package vn.fpt.dbp.vccb.service.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.domain.limit.LimitDetail;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.DBPRestTemplateFactory;
import vn.fpt.util.rest.RestResponse;

@Service
public class LimitService {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;
	
	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	private LimitRepository limitRepository;
		
	@Transactional
	public Limit saveAsDraft(Limit request) throws Exception {
		Limit limit = request.getId() == null ? null : limitRepository.findOne(request.getId());
		
		if (limit != null) {
		
			if(!Status.SAVE_DRAFT.equalsIgnoreCase(limit.getWorkflowStatus()))
			{
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
		}

		return updateLimitInfo(request, Status.SAVE_DRAFT);
	}
	
	@Transactional
	public Limit sendForApprove(Limit request) throws Exception {
		String status = Status.SEND_FOR_APPROVE;
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if(!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		if(request.getAssignee() != null && request.getAssignee().getId() != null){
			status = Status.ASSIGNED;
		}
		
		return updateLimitInfo(request, status);
		
	}
	
	@Transactional
	public Limit assign(Limit request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getAssignee() == null || request.getAssignee().getId() == null){
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		
		if(!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus()) && 
				!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		return updateLimitInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public Limit returnLimit(Limit request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
				
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		Limit limit = limitRepository.findOne(request.getId());
		
		limit.setAssignee(null);
		limit.setAssignedDate(null);
		limit.setWorkflowStatus(Status.SEND_FOR_APPROVE);
		
		return limitRepository.save(limit);
		//return updateLimitInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public Limit approve(Limit request) throws Exception {
		
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		System.out.print("----Start to approve ");
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		//Luu y: Can findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc ra truoc khi approve request.
		Limit originalLimit = limitRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(request.getCode(),Status.APPROVED);
				
		/* Do can thiet dung origianlID de kiem qua quan he la version
		 * nen tat ca cac record ma co quan he version thi deu co cung originalID
		 * Vi the, first version record cung can gan originalId = ID cua chinh no
		 * Truoc khi approve
		 */
		if(request.getOriginalId() == null){
			request.setOriginalId(request.getId());
		}
		
		Limit approvedLimit = updateLimitInfo(request, Status.APPROVED);
		
		if (originalLimit != null && approvedLimit != null )
		{
			System.out.println("++++++Start switch Limit++++++");
			
			Long newId = approvedLimit.getId();
			Long originalId = originalLimit.getId();
			
			System.out.println("-------- newId = " + newId);
			System.out.println("-------- originalId = " + originalId);
			
			swapLimit(newId, originalId);
			
			//Sau khi swap thi can Get lai approveLimit voi Id của originalId
			approvedLimit = limitRepository.findOne(originalId);
			
			/*
			System.out.println("-------- new approvedLimit = " + approvedLimit);
			System.out.println("-------- new approvedLimit.getId() = " + approvedLimit.getId());
			System.out.println("-------- new approvedLimit.getOriginalId() = " + approvedLimit.getOriginalId());
			System.out.println("-------- new approvedLimit.getLimitDetails() = " + approvedLimit.getLimitDetails());
			for(LimitDetail lmdt : approvedLimit.getLimitDetails())
			{
				System.out.println("-------- lmdt.getId() = " + lmdt.getId());
			}
			*/
		}
		
		//-------------------------------
		//now create limit in primary database
		//if (!isPrimary()) { //!Status.APPROVED.equalsIgnoreCase(request.getWorkflowStatus())) {
		String restUrl = apiGatewayUrl + "/tellerapp/admin/api/limit/approved/createinprimary";
		Limit primaryLimit = new Limit();
			
		
		beanUtils.copyProperties(primaryLimit, approvedLimit);				

		if (approvedLimit.getOriginalId() != null) {
			primaryLimit.setId(approvedLimit.getOriginalId()); //this is important to update the right record in primary db
			primaryLimit.setOriginalId(approvedLimit.getId()); //to keep reference to primary record
		}
		
		System.out.println("-------Start primaryLimit Json:");
		System.out.println(new DBPRestTemplateFactory().objectMapper().writeValueAsString(primaryLimit));
		System.out.println("-------End primaryLimit Json:");
		
	    RestResponse<Limit> restResponse = vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, primaryLimit);
		System.out.println("-------Finish vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, primaryLimit)");
			
		if (restResponse==null || restResponse.getStatus() != 0) {
			System.out.println("-------Eror here-----------------");	
			throw new Exception(restResponse.getErrorMessage());
		}
		
		//}
		System.out.println("-------End Approve");
		return approvedLimit;
	}
	
	@Transactional
	public Limit approveInPrimary(Limit request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		System.out.println("+++Start to run function approveInPrimary");
		
		Limit approvedLimit = updateLimitInfo(request, Status.APPROVED);
		
		System.out.print("+++End to run function approveInPrimary");
		
		return approvedLimit;
	}
	
	@Transactional
	public Limit reject(Limit request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		Limit rejectedLimit =  updateLimitInfo(request, Status.REJECTED);
		
		return rejectedLimit;
	}
	
	@Transactional
	public Limit deleteSaveDraft(Limit request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		Limit deletedLimit = limitRepository.findOne(request.getId());
			
		if( ! Status.SAVE_DRAFT.equalsIgnoreCase(deletedLimit.getWorkflowStatus())){
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			
		}
		
		limitRepository.delete(deletedLimit);
		return new Limit(deletedLimit.getId());
	}

	/*
	 * Updata data and status of Limit
	 */
	private Limit updateLimitInfo(Limit request, String status) throws Exception {
		
		//System.out.print("----Start to run updateLimitInfo");
		/*
		if (request !=null)
		{
			System.out.println("+++++ Before Copy Start +++++");
			System.out.println("---- primaryLimit = " + request);
			System.out.println("---- primaryLimit.getId() = " + request.getId());
			System.out.println("---- primaryLimit.getCode() = " + request.getCode());
			System.out.println("---- primaryLimit.getLimitDetails() = " + request.getLimitDetails());
			if (request.getLimitDetails()!=null)
			{
				System.out.print("---- primaryLimit.getLimitDetails().size() = " + request.getLimitDetails().size());
				for (LimitDetail lm : request.getLimitDetails()) {
					System.out.println("---- primaryLimitDetail.Id = " +lm.getId() );
					System.out.println("---- primaryLimitDetail.getBranch = " + lm.getBranch());
					if (lm.getBranch() != null)
					{
						System.out.println("---- primaryLimitDetail.getBranch.name = " + lm.getBranch().getName());
					}
					System.out.println("---- primaryLimitDetail.Limit = " +lm.getLimit() );
				}
			}
			System.out.println("+++++ Before Copy End +++++");
		}
		*/
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		Limit limit = null;
		
		if(request.getId() != null){
			limit = limitRepository.findOne(request.getId());
			
		}
			
		if (limit != null) {
			//Remove old detail limit
			//System.out.println("-------Start: Old detail limit -------");
			for(LimitDetail lm : limit.getLimitDetails()){
				lm.setLimit(null);
				
			}
			limit.getLimitDetails().clear();
			//System.out.println("-------End: Old detail limit -------");
			
			//khong can remove old comments ?? boi vi chi luu cac comment moi xuong DB, comment cu khong duoc edit.
			//Tuy nhien khi xu ly history se bi sai--> do do can xoa cai cu 
			
			for(Comment comment : limit.getComments())
			{
				comment.setWorkFlowModel(null);
			}
			limit.getComments().clear();
			
			beanUtils.copyProperties(limit, request);

		} else {
			limit = request;
		}
		//set data limit detail	
		if (limit.getLimitDetails() != null && limit.getLimitDetails().size() > 0) {
			for (LimitDetail lm : limit.getLimitDetails()) {
					lm.setLimit(limit);
				}
		}
		//set data comments
		if(limit.getComments() != null && limit.getComments().size() > 0)
		{
			//System.out.println("++++ Start Update comment ++++");
			//System.out.println("----- limit.getComments().size() = " + limit.getComments().size());
			for(Comment comment : limit.getComments())
			{
				comment.setWorkFlowModel(limit);
				//System.out.println("---- limit.comment = " + comment.getContent());
			}
			//System.out.println("++++ End Update comment ++++");
		}
		
		/*
		if (limit !=null)
		{
			System.out.println("++++ After Copy Start ++++");
			System.out.println("---- primaryLimit = " + limit);
			System.out.println("---- primaryLimit.getId() = " + limit.getId());
			System.out.println("---- primaryLimit.getCode() = " + limit.getCode());
			System.out.println("---- primaryLimit.getLimitDetails() = " + limit.getLimitDetails());
			if (limit.getLimitDetails()!=null)
			{
				System.out.println("---- primaryLimit.getLimitDetails().size() = " + limit.getLimitDetails().size());
				for (LimitDetail lm : limit.getLimitDetails()) {
					System.out.println("---- primaryLimitDetail.Id = " +lm.getId() );
					System.out.println("---- primaryLimitDetail.getBranch = " + lm.getBranch());
					if (lm.getBranch() != null)
					{
						System.out.println("---- primaryLimitDetail.getBranch.name = " + lm.getBranch().getName());
					}
					System.out.println("---- primaryLimitDetail.Limit = " +lm.getLimit() );
				}
			}
			System.out.println("++++ After Copy End ++++");
		}
		*/
		
		limit.setWorkflowStatus(status);
		System.out.println("----End to run updateLimitInfo");
		
		//System.out.println(new DBPRestTemplateFactory().objectMapper().writeValueAsString(limit));
		/*
		System.out.println("----TO CHECK DUPLICATE ID ISSUE----");
		
		if (limit.getLimitDetails() != null) {
			for (LimitDetail ld : limit.getLimitDetails()) {
				ld.setLimit(null);
				System.out.println(new DBPRestTemplateFactory().objectMapper().writeValueAsString(ld));
				ld.setLimit(limit);
			}
			
		}
		System.out.println("----END OF CHECKING DUPLICATE ID ISSUE----");
		*/
		return limitRepository.save(limit);
	}
	
	
	private void swapLimit(Long firstId, Long secondId) throws Exception {
		Limit firstLimit, secondLimit;
		Limit tempFirstLimit, tempSecondLimit, emptyLimit;
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		System.out.println("******** Start:swapLimit *********");
		
		firstLimit = limitRepository.findOne(firstId);
		secondLimit = limitRepository.findOne(secondId);
		
		tempFirstLimit = new Limit();
		tempSecondLimit = new Limit();
		emptyLimit = new Limit();
		
		if (firstLimit != null && secondLimit != null){
			
			beanUtils.copyProperties(tempFirstLimit, firstLimit);
			beanUtils.copyProperties(tempSecondLimit, secondLimit);
			emptyLimit.setId(firstId);
			
			//Xu ly cho firstLimit
			tempFirstLimit.setId(secondId);
			for(LimitDetail ldFirst : tempFirstLimit.getLimitDetails())
			{
				ldFirst.setLimit(tempFirstLimit);
			}
			
			for(Comment commentFirst : tempFirstLimit.getComments())
			{
				commentFirst.setWorkFlowModel(tempFirstLimit);
			}
			
			//Xu ly cho secondLimit
			
			tempSecondLimit.setId(firstId);
			for(LimitDetail ldSecond : tempSecondLimit.getLimitDetails())
			{
				ldSecond.setLimit(tempSecondLimit);
			}
				
			for(Comment commentSecond : tempSecondLimit.getComments())
			{
				commentSecond.setWorkFlowModel(tempSecondLimit);
			}
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
			limitRepository.save(emptyLimit);
			limitRepository.save(tempFirstLimit);
			limitRepository.save(tempSecondLimit);
		}
	}
	/*
	public static void main(String args[]) throws Exception {
		String restUrl = "http://10.86.202.224/tellerapp/admin/api/limit/approved/createinprimary";
		String json = "{\"id\":422,\"potentialAssignees\":[],\"originalId\":null,\"workflowStatus\":\"APPROVED\",\"taskId\":12374,\"processInstanceId\":8876,\"processDeploymentId\":null,\"referenceCode\":\"17041327672\",\"createdDate\":1492054566853,\"createdBy\":null,\"assignedDate\":null,\"assignee\":null,\"approvedDate\":1492054617777,\"approvedBy\":{\"id\":40,\"potentialAssignees\":null,\"originalId\":null,\"workflowStatus\":null,\"taskId\":null,\"processInstanceId\":null,\"processDeploymentId\":null,\"referenceCode\":null,\"createdDate\":null,\"createdBy\":null,\"assignedDate\":null,\"assignee\":null,\"approvedDate\":null,\"approvedBy\":null,\"fromDate\":null,\"toDate\":null,\"isTemplate\":null,\"cifCode\":null,\"customerName\":null,\"employeeCode\":null,\"username\":null,\"password\":null,\"email\":null,\"phone\":null,\"status\":null,\"loginStatus\":null,\"level\":null,\"isAdmin\":null,\"isWorkOnCif\":null,\"branch\":null,\"lastPasswordChangedDate\":null,\"effectedDate\":null,\"expiredDate\":null,\"startTime\":null,\"endTime\":null,\"tills\":null,\"vaults\":null,\"userRoles\":null,\"userPermission\":null,\"userCurrency\":null,\"userCustomerGroup\":null,\"limitGroup\":null,\"restrictUserBranch\":null,\"restrictUserCurrency\":null,\"restrictUserCustomerGroup\":null,\"restrictUserFunction\":null,\"restrictUserProduct\":null},\"fromDate\":null,\"toDate\":null,\"code\":\"BBB\",\"name\":\"BBB\",\"status\":null,\"limitDetails\":[{\"id\":1850,\"limit\":422,\"branch\":{\"id\":1,\"code\":\"BR1\",\"name\":\"Branch 1\",\"description\":null},\"currency\":{\"id\":1,\"code\":\"VND\",\"name\":\"VND\",\"description\":null},\"customerGroup\":{\"id\":1,\"code\":\"G1\",\"name\":\"Group 1\",\"type\":null,\"description\":null},\"function\":{\"id\":1,\"code\":\"F1\",\"name\":\"Function 1\",\"screen\":null,\"module\":null,\"description\":null},\"product\":{\"id\":280,\"potentialAssignees\":[],\"originalId\":null,\"workflowStatus\":\"APPROVED\",\"taskId\":null,\"processInstanceId\":null,\"processDeploymentId\":null,\"referenceCode\":null,\"createdDate\":null,\"createdBy\":null,\"assignedDate\":null,\"assignee\":null,\"approvedDate\":null,\"approvedBy\":null,\"fromDate\":null,\"toDate\":null,\"code\":\"PR1\",\"name\":\"Product 1\",\"description\":null},\"limitValue\":0,\"isMustApprove\":true,\"isMustApproveOver\":true,\"isStopWhenOver\":true}]}";
		
		NoDeduplicateIdDeserializationContext deserializationContext = new NoDeduplicateIdDeserializationContext(BeanDeserializerFactory.instance);
        ObjectMapper mapper = new ObjectMapper(null, null, deserializationContext);
        mapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
        Limit limit = mapper.readValue(json.getBytes(), Limit.class);
        RestResponse<Limit> restResponse = vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, limit);
        //RestResponse<Limit> restResponse = (RestResponse<Limit>) RestClient.doPost(restUrl, mapper.writeValueAsString(primaryLimit), RestResponse.class);
		System.out.print("-------Finish vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, primaryLimit)");
		
		if (restResponse.getStatus() != 0) {
			throw new Exception(restResponse.getErrorMessage());
		}

	}
	*/
}
