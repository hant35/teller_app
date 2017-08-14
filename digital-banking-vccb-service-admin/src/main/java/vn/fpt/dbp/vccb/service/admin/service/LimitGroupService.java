package vn.fpt.dbp.vccb.service.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.limit.LimitGroup;
import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitGroupRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class LimitGroupService {
	
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	@Autowired
	private LimitGroupRepository limitGroupRepository;

	@Transactional
	public LimitGroup saveAsDraft(LimitGroup request) throws Exception {
		
		LimitGroup limitGroup = request.getId() == null ? null : limitGroupRepository.findOne(request.getId());
		
		if (limitGroup != null) {
		
			if(!Status.SAVE_DRAFT.equalsIgnoreCase(limitGroup.getWorkflowStatus()))
			{
				throw new Exception(DBPException.RECORD_NOT_EXISTED);
			}
		}

		//request.setWorkflowStatus(Status.SAVE_DRAFT);
		//return limitGroupRepository.save(request);
		return updateLimitGroupInfo(request, Status.SAVE_DRAFT);
	}
	
	@Transactional
	public LimitGroup sendForApprove(LimitGroup request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if(!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		String status = Status.SEND_FOR_APPROVE;
		if(request.getAssignee() != null && request.getAssignee().getId() != null){
			status = Status.ASSIGNED;
		}
		return updateLimitGroupInfo(request, status);
		
	}
	
	@Transactional
	public LimitGroup assign(LimitGroup request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getAssignee() == null || request.getAssignee().getId() == null){
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if(!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus()) &&
				!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateLimitGroupInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public LimitGroup returnLimitGroup(LimitGroup request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		LimitGroup lg = limitGroupRepository.findOne(request.getId());
		
		lg.setAssignedDate(null);
		lg.setAssignee(null);
		lg.setWorkflowStatus(Status.SEND_FOR_APPROVE);
		
		return limitGroupRepository.save(lg);
		//return updateLimitGroupInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public LimitGroup approve(LimitGroup request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		//Luu y: Can findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc ra truoc khi approve request.	
		LimitGroup originalLimitGroup = limitGroupRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(request.getCode(),Status.APPROVED);
		
		/* Do can thiet dung origianlID de kiem qua quan he la version
		 * nen tat ca cac record ma co quan he version thi deu co cung originalID
		 * Vi the, first version record cung can gan originalId = ID cua chinh no
		 * Truoc khi approve
		 */
		if(request.getOriginalId() == null){
			request.setOriginalId(request.getId());
		}
		LimitGroup approvedLimitGroup = updateLimitGroupInfo(request, Status.APPROVED);
		
		if (originalLimitGroup != null && approvedLimitGroup != null ){
			Long newId = approvedLimitGroup.getId();
			Long originalId = originalLimitGroup.getId();
			swapLimitGroup(newId, originalId);
			
			//Sau khi swap thi can Get lai approveLimit voi Id của originalId
			approvedLimitGroup = limitGroupRepository.findOne(originalId);
		}
			
		//now create limitGroup in primary database
		String restUrl = apiGatewayUrl + "/tellerapp/admin/api/limitgroup/approved/createinprimary";
		LimitGroup primaryLimitGroup = new LimitGroup();

		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		beanUtils.copyProperties(primaryLimitGroup, approvedLimitGroup);
			
		if (approvedLimitGroup.getOriginalId() != null) {
			
			primaryLimitGroup.setId(approvedLimitGroup.getOriginalId()); //this is important to update the right record in primary db
			primaryLimitGroup.setOriginalId(approvedLimitGroup.getId()); //to keep reference to primary record	
		} 
		RestResponse<LimitGroup> restResponse = vn.fpt.dbp.vccb.core.rest.limit.LimitGroupService.cud(restUrl, primaryLimitGroup);
		if (restResponse.getStatus() != 0) {
			throw new Exception(restResponse.getErrorMessage());
		}
	
		return approvedLimitGroup;
	}
	
	@Transactional
	public LimitGroup approveInPrimary(LimitGroup request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		LimitGroup approvedGroupLimit = updateLimitGroupInfo(request, Status.APPROVED);
		
		return approvedGroupLimit;
	}
	
	@Transactional
	public LimitGroup reject(LimitGroup request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		
		return updateLimitGroupInfo(request, Status.REJECTED);
		
	}
	
	@Transactional
	public LimitGroup deleteSaveDraft(LimitGroup request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		LimitGroup deletedLimitGroup = limitGroupRepository.findOne(request.getId());
			
		if( ! Status.SAVE_DRAFT.equalsIgnoreCase(deletedLimitGroup.getWorkflowStatus())){
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			
		}
		
		limitGroupRepository.delete(deletedLimitGroup);
		LimitGroup gr = new LimitGroup();
		gr.setId(deletedLimitGroup.getId());
		return gr;
	}

	/*
	 * Updata data and status of LimitGroup
	 */
	private LimitGroup updateLimitGroupInfo(LimitGroup request, String status) throws Exception {
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		LimitGroup limitGroup = null;
		
		if(request.getId() != null){
			limitGroup = limitGroupRepository.findOne(request.getId());
		}
		
		if (limitGroup != null) {
			
			for(Comment comment : limitGroup.getComments())
			{
				comment.setWorkFlowModel(null);
			}
			limitGroup.getComments().clear();
			
			beanUtils.copyProperties(limitGroup, request);
		} else {
			limitGroup = request;
		}
		
		//set data comments
		if(limitGroup.getComments() != null && limitGroup.getComments().size() > 0){
			for(Comment comment : limitGroup.getComments())
			{
				comment.setWorkFlowModel(limitGroup);
			}
		}
		
		limitGroup.setWorkflowStatus(status);

		return limitGroupRepository.save(limitGroup);
	}
	
	private void swapLimitGroup(Long firstId, Long secondId) throws Exception {
		LimitGroup firstLimitGroup, secondLimitGroup;
		LimitGroup tempFirstLimitGroup, tempSecondLimitGroup, emptyFirstLimitGroup, emptySecondLimitGroup;
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		System.out.println("******** Start:swapLimit *********");
		
		firstLimitGroup = limitGroupRepository.findOne(firstId);
		secondLimitGroup = limitGroupRepository.findOne(secondId);
		
		tempFirstLimitGroup = new LimitGroup();
		tempSecondLimitGroup = new LimitGroup();
		emptyFirstLimitGroup = new LimitGroup();
		emptySecondLimitGroup = new LimitGroup();
		
		if (firstLimitGroup != null && secondLimitGroup != null){
			beanUtils.copyProperties(tempFirstLimitGroup, firstLimitGroup);
			beanUtils.copyProperties(tempSecondLimitGroup, secondLimitGroup);
			emptyFirstLimitGroup.setId(firstId);
			emptySecondLimitGroup.setId(secondId);
			
			//Xu ly cho tempFirstLimitGroup
			tempFirstLimitGroup.setId(secondId);
			for(Comment lm : tempFirstLimitGroup.getComments()){
				lm.setWorkFlowModel(tempFirstLimitGroup);
			}
			System.out.println("******** tempFirstLimitGroup *********");
			for(Limit l1 : tempFirstLimitGroup.getLimits() ){
				System.out.println("---- tempFirstLimitGroup.limit.getId = "  + l1.getId());;
				System.out.println("---- tempFirstLimitGroup.limit.getCode = " + l1.getCode());
				System.out.println("---- tempFirstLimitGroup.limit.getName = " + l1.getName());
			}

			//Xu ly cho tempSecondLimitGroup
			tempSecondLimitGroup.setId(firstId);
			for(Comment lm : tempSecondLimitGroup.getComments()){
				lm.setWorkFlowModel(tempSecondLimitGroup);
			}
			
			System.out.println("******** tempSecondLimitGroup *********");
			for(Limit l2 : tempSecondLimitGroup.getLimits() ){
				System.out.println("---- tempSecondLimitGroup.limit.getId = "  + l2.getId());;
				System.out.println("---- tempSecondLimitGroup.limit.getCode = " + l2.getCode());
				System.out.println("---- tempSecondLimitGroup.limit.getName = " + l2.getName());
			}
			
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * Fix for case: ManyToMany
			 * 1. Update row A with null/empty data
			 * 2. Update row B with null/empty data
			 * 3. Update row B with new data (from A data)
			 * 4. Update row A with new data (from B data)
			 */
			LimitGroup A,B,N,M;
			N = limitGroupRepository.save(emptyFirstLimitGroup);
			M = limitGroupRepository.save(emptySecondLimitGroup);
			A = limitGroupRepository.save(tempFirstLimitGroup);
			B = limitGroupRepository.save(tempSecondLimitGroup);
			
			System.out.println("******** N *********");
			for(Limit l0 : N.getLimits() ){
				System.out.println("---- N.limit.getId = "  + l0.getId());;
				System.out.println("---- N.limit.getCode = " + l0.getCode());
				System.out.println("---- N.limit.getName = " + l0.getName());
			}
			System.out.println("******** M *********");
			for(Limit l01 : M.getLimits() ){
				System.out.println("---- M.limit.getId = "  + l01.getId());;
				System.out.println("---- M.limit.getCode = " + l01.getCode());
				System.out.println("---- M.limit.getName = " + l01.getName());
			}
			System.out.println("******** A *********");
			for(Limit l1 : A.getLimits() ){
				System.out.println("---- A.limit.getId = "  + l1.getId());;
				System.out.println("---- A.limit.getCode = " + l1.getCode());
				System.out.println("---- A.limit.getName = " + l1.getName());
			}
			System.out.println("******** B *********");
			for(Limit l2 : B.getLimits() ){
				System.out.println("---- B.limit.getId = "  + l2.getId());;
				System.out.println("---- B.limit.getCode = " + l2.getCode());
				System.out.println("---- B.limit.getName = " + l2.getName());
			}
		}
	}


}
