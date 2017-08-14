package vn.fpt.dbp.vccb.service.param.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeDetail;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeUploadFile;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeUploadFileRepository;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class ExchangeUploadFileService {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;

    @Autowired
    private ExchangeUploadFileRepository exchangeUploadFileRepository;

    @Transactional
    public ExchangeUploadFile saveAsDraft(ExchangeUploadFile request) throws Exception {
        ExchangeUploadFile exchange = request.getId() == null ? null : exchangeUploadFileRepository.findOne(request.getId());

        if (!Status.SAVE_DRAFT.equalsIgnoreCase(exchange.getWorkflowStatus())) {
            throw new Exception("Record is not savedraft");
        }

        return  updateExchangeUploadFileInfo (request, Status.SAVE_DRAFT);
    }

    @Transactional
    public ExchangeUploadFile deleteSaveDraft(ExchangeUploadFile request) throws Exception {
        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }
        ExchangeUploadFile deletedExchangeUploadFile = exchangeUploadFileRepository.findOne(request.getId());

        if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedExchangeUploadFile.getWorkflowStatus())) {
            throw new Exception("Record is not savedraft");
        }

        exchangeUploadFileRepository.delete(deletedExchangeUploadFile);

        return new ExchangeUploadFile(request.getId());
    }

    @Transactional
    public ExchangeUploadFile sendForApproved(ExchangeUploadFile request) throws Exception {

//        if (request.getId() == null) {
//            throw new Exception("Record does not exist");
//        }

        return updateExchangeUploadFileInfo(request, Status.SEND_FOR_APPROVE );

    }

    @Transactional
    public ExchangeUploadFile assign(ExchangeUploadFile request) throws Exception {

//        if (request.getId() == null) {
//            throw new Exception("Record does not exist");
//        }
        if (request.getAssignee() == null || request.getAssignee().getId() == null) {
            throw new Exception("No assignee specified");
        }

        return updateExchangeUploadFileInfo(request, Status.ASSIGNED);
    }

    @Transactional
    public ExchangeUploadFile returnAssigned(ExchangeUploadFile request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getAssignee() == null || request.getAssignee().getId() == null){
            throw new Exception("No assignee specified");
        }

        request.setAssignee(null);

        return updateExchangeUploadFileInfo(request, Status.SEND_FOR_APPROVE);

    }

    @Transactional
    public ExchangeUploadFile reject(ExchangeUploadFile request) throws Exception {
        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception("No approver specified");
        }

        if (request.getComments() == null || request.getComments().size() <= 0){
            throw new Exception("No comment specified");
        }

        return updateExchangeUploadFileInfo(request, Status.REJECTED);
    }

    @Transactional(rollbackFor = Exception.class)
    public ExchangeUploadFile approve(ExchangeUploadFile request) throws Exception {

//        System.out.print("----Start to approve ");

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
            throw new Exception("No approver specified");
        }

        ExchangeUploadFile originalExchangeUploadFile = exchangeUploadFileRepository.findTop1ByFileNameAndUploadDateAndWorkflowStatusOrderByApprovedDateDesc (request.getFileName(), request.getUploadDate(), Status.APPROVED);

//        System.out.print("----Start to call  updateExchangeUploadFileInfo");
        ExchangeUploadFile approvedExchangeUploadFile = updateExchangeUploadFileInfo(request, Status.APPROVED);

        if(originalExchangeUploadFile != null && approvedExchangeUploadFile != null ) {
//            System.out.println("++++++Start switch ExchangeUploadFile++++++");

            Long newId = approvedExchangeUploadFile.getId();
            Long originalId = originalExchangeUploadFile.getId();

//            System.out.println("-------- newId = " + newId);
//            System.out.println("-------- originalId = " + originalId);

            swapExchangeUploadFile(newId, originalId);

            //Sau khi swap thi can Get lai approveProduct voi Id của originalId
            approvedExchangeUploadFile = exchangeUploadFileRepository.findOne(originalId);

//            System.out.println("-------- new approvedExchangeUploadFile = " + approvedExchangeUploadFile);
//            System.out.println("-------- new approvedExchangeUploadFile.getId() = " + approvedExchangeUploadFile.getId());
//            System.out.println("-------- new approvedExchangeUploadFile.getOriginalId() = " + approvedExchangeUploadFile.getOriginalId());
        }

        //now create user in primary database
        //if (!Status.APPROVED.equalsIgnoreCase(request.getWorkflowStatus())) {
        String restUrl = apiGatewayUrl + "/tellerapp/param/api/exchangeuploadfile/approved/createinprimary";
        ExchangeUploadFile primaryExchangeUploadFile = new ExchangeUploadFile();

        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        beanUtils.copyProperties(primaryExchangeUploadFile, approvedExchangeUploadFile);

        if (approvedExchangeUploadFile.getOriginalId() != null) {
            primaryExchangeUploadFile.setId(approvedExchangeUploadFile.getOriginalId()); //this is important to update the right record in primary db
            primaryExchangeUploadFile.setOriginalId(approvedExchangeUploadFile.getId()); //to keep reference to primary record
        }
        RestResponse<ExchangeUploadFile> restResponse = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, primaryExchangeUploadFile);
//        System.out.print("-------Finish vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, primaryExchangeUploadFile)");
        if (restResponse.getStatus() != 0) {
            throw new Exception(restResponse.getErrorMessage());
        }
        return approvedExchangeUploadFile;

    }

    @Transactional(rollbackFor = Exception.class)
    public ExchangeUploadFile approveInPrimary(ExchangeUploadFile request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
            throw new Exception("No approver specified");
        }

//        System.out.print("+++Start to run function approveInPrimary");

        ExchangeUploadFile approvedExchangeUploadFile = updateExchangeUploadFileInfo(request, Status.APPROVED);

//        System.out.print("+++End to run function approveInPrimary");

        return approvedExchangeUploadFile;

    }

    private ExchangeUploadFile updateExchangeUploadFileInfo(ExchangeUploadFile request, String status) throws Exception {
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        ExchangeUploadFile exchangeUploadFile = request.getId() == null ? null : exchangeUploadFileRepository.findOne(request.getId());

        //remove old data
        if (exchangeUploadFile != null) {
            for(Exchange exchange : exchangeUploadFile.getExchanges())
            {
                for(ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
                    exchangeDetail.setExchange(null);
                }
                exchange.getExchangeDetails().clear();

                exchange.setExchangeUploadFile(null);
            }
            exchangeUploadFile.getExchanges().clear();


            for(Comment comment : exchangeUploadFile.getComments())
            {
                comment.setWorkFlowModel(null);
            }
            exchangeUploadFile.getComments().clear();

            beanUtils.copyProperties(exchangeUploadFile, request);

            //remove assignee
            exchangeUploadFile.setAssignee(request.getAssignee());
        } else {
            exchangeUploadFile = request;
        }

        if(request.getExchanges() != null && request.getExchanges().size() > 0){
            for (Exchange exchange : request.getExchanges())
            {
                if(exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0)
                {
                    for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
                        exchangeDetail.setExchange(exchange);
                    }
                }
                exchange.setExchangeUploadFile(exchangeUploadFile);
            }
        }

        //set data comment
        if(request.getComments() != null && request.getComments().size() > 0)
        {
            for(Comment comment : request.getComments())
            {
                comment.setWorkFlowModel(exchangeUploadFile);
            }
        }

        exchangeUploadFile.setWorkflowStatus(status);
//        System.out.print("----End to run updateExchangeUploadFileInfo");

        return exchangeUploadFileRepository.save(exchangeUploadFile);
    }

    private void swapExchangeUploadFile(Long firstId, Long secondId) throws Exception {
        ExchangeUploadFile firstExchangeUploadFile, secondExchangeUploadFile;
        ExchangeUploadFile tempFirstExchangeUploadFile, tempSecondExchangeUploadFile, emptyExchangeUploadFile;
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

//        System.out.println("******** Start:swapExchangeUploadFile *********");

        firstExchangeUploadFile = exchangeUploadFileRepository.findOne(firstId);
        secondExchangeUploadFile = exchangeUploadFileRepository.findOne(secondId);

        tempFirstExchangeUploadFile = new ExchangeUploadFile();
        tempSecondExchangeUploadFile = new ExchangeUploadFile();
        emptyExchangeUploadFile = new ExchangeUploadFile();

        if (firstExchangeUploadFile != null && secondExchangeUploadFile != null){

            beanUtils.copyProperties(tempFirstExchangeUploadFile, firstExchangeUploadFile);
            beanUtils.copyProperties(tempSecondExchangeUploadFile, secondExchangeUploadFile);
            emptyExchangeUploadFile.setId(firstId);

            //Xu ly cho firstExchangeUploadFile
            tempFirstExchangeUploadFile.setId(secondId);

            for(Comment comment : tempFirstExchangeUploadFile.getComments())
            {
                comment.setWorkFlowModel(tempFirstExchangeUploadFile);
            }

            //Xu ly cho secondExchangeUploadFile
            tempSecondExchangeUploadFile.setId(firstId);

            for(Comment comment : tempSecondExchangeUploadFile.getComments())
            {
                comment.setWorkFlowModel(tempSecondExchangeUploadFile);
            }

			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
            exchangeUploadFileRepository.save(emptyExchangeUploadFile);
            exchangeUploadFileRepository.save(tempFirstExchangeUploadFile);
            exchangeUploadFileRepository.save(tempSecondExchangeUploadFile);
        }
    }
}
