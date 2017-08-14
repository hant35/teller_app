package vn.fpt.dbp.vccb.service.cif.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.customer.AccountNoReserved;
import vn.fpt.dbp.vccb.core.domain.customer.repository.AccountNoReservedRepository;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class AccountNoReservedService {

    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    AccountNoReservedRepository accountNoReservedRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountNoReservedService.class);

    @Transactional
    public AccountNoReserved saveAsDraft(AccountNoReserved request) throws Exception {
        AccountNoReserved accountNoReserved = request.getId() == null ? null : accountNoReservedRepository.findOne(request.getId());

        if (accountNoReserved != null) {
            if (!Status.SAVE_DRAFT.equalsIgnoreCase(accountNoReserved.getWorkflowStatus())) {
                throw new Exception("Record is not savedraft");
            }
        }

        return updateAccountNoReservedInfo(request, Status.SAVE_DRAFT);
    }

    @Transactional
    public AccountNoReserved sendForApprove(AccountNoReserved request) throws Exception {
    	String status = Status.SEND_FOR_APPROVE;
    	
        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }
        
        if (!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())) {
			Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			throw e;
		}
		if (request.getAssignee() != null && request.getAssignee().getId() != null) {
			status = Status.ASSIGNED;
		}

        return updateAccountNoReservedInfo(request, status);
    }

    @Transactional
    public AccountNoReserved assign(AccountNoReserved request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if ((request.getAssignee() == null || request.getAssignee().getId() == null) && StringUtils.isEmpty(request.getAssignGroup())){
            throw new Exception(DBPException.NO_ASSIGNEE + " NO_ASSIGN_GROUP");
        }
        
        if (!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus())
				&& !Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())) {
			Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			throw e;
		}

        return updateAccountNoReservedInfo(request, Status.ASSIGNED);
    }

    @Transactional
    public AccountNoReserved returnAssign(AccountNoReserved request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if ((request.getAssignee() == null || request.getAssignee().getId() == null) && StringUtils.isEmpty(request.getAssignGroup())){
            throw new Exception(DBPException.NO_ASSIGNEE + " NO_ASSIGN_GROUP");
        }
        
        if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			throw e;
		}

        request.setAssignee(null);
        request.setAssignedDate(null);
        //request.setAssignGroup(null);

        return updateAccountNoReservedInfo(request, Status.SEND_FOR_APPROVE);
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountNoReserved approve(AccountNoReserved request) throws Exception {

//        System.out.print("----Start to approve ");

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
            throw new Exception(DBPException.NO_APPROVER);
        }
        
        if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			throw e;
		}

        AccountNoReserved originalAccountNoReserved =
                accountNoReservedRepository.findTop1ByBranchAndCurrentNoAndQuantityAndEndNoAndWorkflowStatusOrderByApprovedDateDesc(
                        request.getBranch(), request.getCurrentNo(), request.getQuantity(), request.getEndNo(), Status.APPROVED);

        if(request.getOriginalId() == null){
            request.setOriginalId(request.getId());
        }

        AccountNoReserved approvedAccountNoReserved = updateAccountNoReservedInfo(request, Status.APPROVED);

        logger.info("api/accountNoReservedService/approved/create: Go to CreateInPrimary");

        if(originalAccountNoReserved != null && approvedAccountNoReserved != null ) {

            Long newId = approvedAccountNoReserved.getId();
            Long originalId = originalAccountNoReserved.getId();

            swapAccountNoReserved(newId, originalId);

            //Sau khi swap thi can Get lai approveAccountNoReserved voi Id của originalId
            approvedAccountNoReserved = accountNoReservedRepository.findOne(originalId);
        }

        //now create accountNoReserved in primary database
        String restUrl = apiGatewayUrl + "/tellerapp/cif/api/accountnoreserved/approved/createinprimary";
        AccountNoReserved primaryAccountNoReserved = new AccountNoReserved();

        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        beanUtils.copyProperties(primaryAccountNoReserved, approvedAccountNoReserved);

        if (approvedAccountNoReserved.getOriginalId() != null) {
            primaryAccountNoReserved.setId(approvedAccountNoReserved.getOriginalId()); //this is important to update the right record in primary db
            primaryAccountNoReserved.setOriginalId(approvedAccountNoReserved.getId()); //to keep reference to primary record
        }

        RestResponse<AccountNoReserved> restResponse = vn.fpt.dbp.vccb.core.rest.customer.AccountNoReservedService.cud(restUrl, primaryAccountNoReserved);
        if (restResponse.getStatus() != 0) {
            throw new Exception(restResponse.getErrorMessage());
        }

        logger.info("-------End Approve");
        return approvedAccountNoReserved;

    }

    @Transactional(rollbackFor = Exception.class)
    public AccountNoReserved approveInPrimary(AccountNoReserved request) throws Exception{

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception(DBPException.NO_APPROVER);
        }

        AccountNoReserved approvedAccountNoReserved = updateAccountNoReservedInfo(request, Status.APPROVED);

        logger.info("+++End to run function approveInPrimary");

        return approvedAccountNoReserved;
    }

    @Transactional
    public AccountNoReserved reject(AccountNoReserved request) throws Exception {

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

        if (request.getComments() == null || request.getComments().size() <= 0 ){
            throw new Exception(DBPException.NO_COMMENT);
        }

        return updateAccountNoReservedInfo(request, Status.REJECTED);

    }

    @Transactional
    public AccountNoReserved deleteSaveDraft(AccountNoReserved request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);

        }
        AccountNoReserved deletedAccountNoReserved = accountNoReservedRepository.findOne(request.getId());

        if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedAccountNoReserved.getWorkflowStatus())) {
            throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);

        }

        accountNoReservedRepository.delete(deletedAccountNoReserved);
        return new AccountNoReserved(request.getId());
    }

    private AccountNoReserved updateAccountNoReservedInfo(AccountNoReserved request, String status) throws Exception{
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        AccountNoReserved accountNoReserved = null;

        if(request.getId() != null)
        {
            accountNoReserved = accountNoReservedRepository.findOne(request.getId());
        }

        //remove old data
        if(accountNoReserved != null)
        {
            for(Comment comment : accountNoReserved.getComments())
            {
                comment.setWorkFlowModel(null);
            }
            accountNoReserved.getComments().clear();

            beanUtils.copyProperties(accountNoReserved, request);

            //beanUtils.copyPropertie không copy những properties có value Null.
            //Nên cần update những trường này trong trường hợp value mới là Null.
            accountNoReserved.setAssignee(request.getAssignee());
            //Khi Save_Draft sẽ có trường hợp save đè bằng value Null cho cả các field required
            if(Status.SAVE_DRAFT.equalsIgnoreCase(accountNoReserved.getWorkflowStatus())){
                accountNoReserved = request;
            }
        }
        else
        {
            accountNoReserved = request;
        }

        if(accountNoReserved.getComments() != null && accountNoReserved.getComments().size() > 0)
        {
            for(Comment comment : accountNoReserved.getComments())
            {
                comment.setWorkFlowModel(accountNoReserved);
            }
        }

        accountNoReserved.setWorkflowStatus(status);
        logger.info("----End to run updateCustomerInfo");
        return accountNoReservedRepository.save(accountNoReserved);

    }

    private void swapAccountNoReserved(Long firstId, Long secondId) throws Exception {
        AccountNoReserved firstAccountNoReserved, secondAccountNoReserved;
        AccountNoReserved tempFirstAccountNoReserved, tempSecondAccountNoReserved, emptyAccountNoReserved;
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

//        System.out.println("******** Start:swapProduct *********");

        firstAccountNoReserved = accountNoReservedRepository.findOne(firstId);
        secondAccountNoReserved = accountNoReservedRepository.findOne(secondId);

        tempFirstAccountNoReserved = new AccountNoReserved();
        tempSecondAccountNoReserved = new AccountNoReserved();
        emptyAccountNoReserved = new AccountNoReserved();

        if (firstAccountNoReserved != null && secondAccountNoReserved != null){

            beanUtils.copyProperties(tempFirstAccountNoReserved, firstAccountNoReserved);
            beanUtils.copyProperties(tempSecondAccountNoReserved, secondAccountNoReserved);
            emptyAccountNoReserved.setId(firstId);

            //Xu ly cho firstAccountNoReserved
            tempFirstAccountNoReserved.setId(secondId);

            for(Comment comment : tempFirstAccountNoReserved.getComments())
                {
                comment.setWorkFlowModel(tempFirstAccountNoReserved);
            }

            //Xu ly cho secondAccountNoReserved

            tempSecondAccountNoReserved.setId(firstId);

            for(Comment comment : tempSecondAccountNoReserved.getComments())
            {
                comment.setWorkFlowModel(tempSecondAccountNoReserved);
            }
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
            accountNoReservedRepository.save(emptyAccountNoReserved);
            accountNoReservedRepository.save(tempFirstAccountNoReserved);
            accountNoReservedRepository.save(tempSecondAccountNoReserved);
        }
    }

    public User getUser(String username){

        if(StringUtils.isEmpty(username)){
            return null;
        }

        return userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);
    }
}
