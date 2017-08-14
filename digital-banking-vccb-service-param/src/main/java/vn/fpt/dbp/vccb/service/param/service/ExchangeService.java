package vn.fpt.dbp.vccb.service.param.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.exchange.*;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeDetailRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExchangeService {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	private ExchangeRepository exchangeRepository;
	@Autowired
	private ExchangeDetailRepository exchangeDetailRepository;

	@Transactional
	public Exchange saveAsDraft(Exchange request) throws Exception {
		Exchange exchange = request.getId() == null ? null : exchangeRepository.findOne(request.getId());

		if (exchange != null) {
			if (!Status.SAVE_DRAFT.equalsIgnoreCase(exchange.getWorkflowStatus())) {
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
		}

		return updateExchangeInfo(request, Status.SAVE_DRAFT);
	}

	@Transactional
	public Exchange deleteSaveDraft(Exchange request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		Exchange deletedExchange = exchangeRepository.findOne(request.getId());

		if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedExchange.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}

		deletedExchange.getExchangeDetails().clear();
		exchangeRepository.delete(deletedExchange);

		return new Exchange(request.getId());
	}

	@Transactional
	public Exchange sendForApproved(Exchange request) throws Exception {

		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		return updateExchangeInfo(request, Status.SEND_FOR_APPROVE );

	}

	@Transactional
	public Exchange assign(Exchange request) throws Exception {

		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if(!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus()) && 
				!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}

		return updateExchangeInfo(request, Status.ASSIGNED);
	}

	@Transactional
	public Exchange returnAssigned(Exchange request) throws Exception {

		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		if (request.getAssignee() == null || request.getAssignee().getId() == null){
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}

		request.setAssignee(null);

		return updateExchangeInfo(request, Status.SEND_FOR_APPROVE);

	}

	@Transactional
	public Exchange reject(Exchange request) throws Exception {
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

		if (request.getComments() == null || request.getComments().size() <= 0){
			throw new Exception("No comment specified");
		}

		return updateExchangeInfo(request, Status.REJECTED);
	}

	@Transactional(rollbackFor = Exception.class)
	public Exchange approve(Exchange request) throws Exception {

		System.out.print("----Start to approve ");

		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}

		Exchange originalExchange = exchangeRepository.findTop1ByAreaAndBranchAndCurrencyFromAndCurrencyToAndWorkflowStatusOrderByApprovedDateDesc(request.getArea(), request.getBranch(), request.getCurrencyFrom(), request.getCurrencyTo(), Status.APPROVED);

		if(request.getOriginalId() == null){
			request.setOriginalId(request.getId());
		}

//		Update effectDate là approving date theo y.cầu business ngày 11/07/2017
		Set<ExchangeDetail> exchangeDetailSetTmp = new HashSet<ExchangeDetail>();
		for ( ExchangeDetail exchangeDetailItem: request.getExchangeDetails() )
		{
			exchangeDetailItem.setEffectedDate( new Date() );
//			...
			exchangeDetailSetTmp.add( exchangeDetailItem );
		}
		request.setExchangeDetails(exchangeDetailSetTmp);

		System.out.print("----Start to call  updateExchangeInfo");
		Exchange approvedExchange = updateExchangeInfo(request, Status.APPROVED);

		if(originalExchange != null && approvedExchange != null ) {
			System.out.println("++++++Start switch Exchange++++++");

			Long newId = approvedExchange.getId();
			Long originalId = originalExchange.getId();

			System.out.println("-------- newId = " + newId);
			System.out.println("-------- originalId = " + originalId);

			swapExchange(newId, originalId);

			//Sau khi swap thi can Get lai approveProduct voi Id của originalId
			approvedExchange = exchangeRepository.findOne(originalId);

			System.out.println("-------- new approvedExchange = " + approvedExchange);
			System.out.println("-------- new approvedExchange.getId() = " + approvedExchange.getId());
			System.out.println("-------- new approvedExchange.getOriginalId() = " + approvedExchange.getOriginalId());
			System.out.println("-------- new approvedExchange.getExchangeDetails() = " + approvedExchange.getExchangeDetails());
			for(ExchangeDetail exchangeDetail : approvedExchange.getExchangeDetails())
			{
				System.out.println("-------- approvedExchangeDetail.getId() = " + exchangeDetail.getId());
			}
		}

		//now create user in primary database
		//if (!Status.APPROVED.equalsIgnoreCase(request.getWorkflowStatus())) {
		String restUrl = apiGatewayUrl + "/tellerapp/param/api/exchange/approved/createinprimary";
		Exchange primaryExchange = new Exchange();

		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		beanUtils.copyProperties(primaryExchange, approvedExchange);

		if (approvedExchange.getOriginalId() != null) {
			primaryExchange.setId(approvedExchange.getOriginalId()); //this is important to update the right record in primary db
			primaryExchange.setOriginalId(approvedExchange.getId()); //to keep reference to primary record
		}
		RestResponse<Exchange> restResponse = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, primaryExchange);
		System.out.print("-------Finish vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, primaryExchange)");
		if (restResponse.getStatus() != 0) {
			throw new Exception(restResponse.getErrorMessage());
		}
		return approvedExchange;

	}

	@Transactional(rollbackFor = Exception.class)
	public Exchange approveInPrimary(Exchange request) throws Exception {

		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}

		System.out.print("+++Start to run function approveInPrimary");

		Exchange approvedExchange = updateExchangeInfo(request, Status.APPROVED);

		System.out.print("+++End to run function approveInPrimary");

		return approvedExchange;

	}

	private Exchange updateExchangeInfo(Exchange request, String status) throws Exception {
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		Exchange exchange = request.getId() == null ? null : exchangeRepository.findOne(request.getId());

		//remove old data
		if (exchange != null) {
			for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
				exchangeDetail.setExchange(null);
			}
			exchange.getExchangeDetails().clear();

			for(Comment comment : exchange.getComments())
			{
				comment.setWorkFlowModel(null);
			}
			exchange.getComments().clear();

			beanUtils.copyProperties(exchange, request);

			//beanUtils.copyPropertie không copy những properties có value Null.
			//Nên cần update những trường này trong trường hợp value mới là Null.
			exchange.setAssignee(request.getAssignee());
			//Khi Save_Draft sẽ có trường hợp save đè bằng value Null cho cả các field required
			if(Status.SAVE_DRAFT.equalsIgnoreCase(exchange.getWorkflowStatus())){
				exchange = request;
			}
		} else {
			exchange = request;
		}

		if(exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0)
		{
			for(ExchangeDetail exchangeDetail : exchange.getExchangeDetails())
			{
				exchangeDetail.setExchange(exchange);
			}
		}

		//set data comment
		if(request.getComments() != null && request.getComments().size() > 0)
		{
			for(Comment comment : request.getComments())
			{
				comment.setWorkFlowModel(exchange);
			}
		}

		exchange.setWorkflowStatus(status);
		System.out.print("----End to run updateExchangeInfo");

		return exchangeRepository.save(exchange);
	}

	private void swapExchange(Long firstId, Long secondId) throws Exception {
		Exchange firstExchange, secondExchange;
		Exchange tempFirstExchange, tempSecondExchange, emptyExchange;
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

		System.out.println("******** Start:swapExchange *********");

		firstExchange = exchangeRepository.findOne(firstId);
		secondExchange = exchangeRepository.findOne(secondId);

		tempFirstExchange = new Exchange();
		tempSecondExchange = new Exchange();
		emptyExchange = new Exchange();

		if (firstExchange != null && secondExchange != null){

			beanUtils.copyProperties(tempFirstExchange, firstExchange);
			beanUtils.copyProperties(tempSecondExchange, secondExchange);
			emptyExchange.setId(firstId);

			//Xu ly cho firstExchange
			tempFirstExchange.setId(secondId);

			for(ExchangeDetail exchangeDetail : tempFirstExchange.getExchangeDetails())
			{
				exchangeDetail.setExchange(tempFirstExchange);

			}

			for(Comment comment : tempFirstExchange.getComments())
			{
				comment.setWorkFlowModel(tempFirstExchange);
			}

			//Xu ly cho secondExchange
			tempSecondExchange.setId(firstId);
			for(ExchangeDetail exchangeDetail : tempSecondExchange.getExchangeDetails())
			{
				exchangeDetail.setExchange(tempSecondExchange);
			}

			for(Comment comment : tempSecondExchange.getComments())
			{
				comment.setWorkFlowModel(tempSecondExchange);
			}

			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
			exchangeRepository.save(emptyExchange);
			exchangeRepository.save(tempFirstExchange);
			exchangeRepository.save(tempSecondExchange);
		}
	}





}
