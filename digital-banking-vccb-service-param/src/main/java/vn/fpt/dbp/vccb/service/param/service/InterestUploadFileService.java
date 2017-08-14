package vn.fpt.dbp.vccb.service.param.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;
import vn.fpt.dbp.vccb.core.domain.interest.InterestUploadFile;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestParameterRepository;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestRepository;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestUploadFileRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.util.rest.RestResponse;

@Service
public class InterestUploadFileService {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	private InterestUploadFileRepository interestUploadFileRepository;

	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private InterestParameterRepository interestParameterRepository;

	public InterestUploadFile detailInterestUploadFile(String id) throws Exception {
		InterestUploadFile interest = new InterestUploadFile(id);
		interest = interestUploadFileRepository.findOne(interest.getId());

		if (interest == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		return interest;
	}

	@Transactional(rollbackFor = Exception.class)
	public InterestUploadFile saveAsDraft(InterestUploadFile request) throws Exception {
		if (request == null || request.getInterests() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		InterestUploadFile interestUploadFile = request.getId() == null ? null
				: interestUploadFileRepository.findOne(request.getId());
		if (interestUploadFile != null) {
			if (!Status.SAVE_DRAFT.equalsIgnoreCase(interestUploadFile.getWorkflowStatus())) {
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
			request = interestUploadFileRepository.save(interestUploadFile);
		} else {
			try {
				for (Interest interest : request.getInterests()) {
					if (interest.getInterestParameters() != null) {
						for (InterestParameter interestParameter : interest.getInterestParameters()) {
							interestParameter.setInterest(interest);
						}
					}
					interest.setInterestUploadFile(request);
					interest.setCreatedBy(request.getCreatedBy());
					interest.setCreatedDate(request.getCreatedDate());
				}
				request.setWorkflowStatus(Status.SAVE_DRAFT);
				request = interestUploadFileRepository.save(request);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return request;
	}

	@Transactional(rollbackFor = Exception.class)
	public InterestUploadFile deleteSaveDraft(InterestUploadFile request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		InterestUploadFile deletedInterestUploadFile = interestUploadFileRepository.findOne(request.getId());
		if (deletedInterestUploadFile == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		try {
			if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedInterestUploadFile.getWorkflowStatus())) {
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
			if (deletedInterestUploadFile.getInterests() != null) {
				for (Interest interest : deletedInterestUploadFile.getInterests()) {
					if (interest.getInterestParameters() != null) {
						for (InterestParameter interestParameter : interest.getInterestParameters()) {
							interestParameter.setInterest(null);
							interestParameterRepository.delete(interestParameter);
						}
					}
					interest.getInterestParameters().clear();
					interestRepository.delete(interest);
				}
			}
			deletedInterestUploadFile.getInterests().clear();
			interestUploadFileRepository.delete(deletedInterestUploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deletedInterestUploadFile;
	}

	@Transactional(rollbackFor = Exception.class)
	public InterestUploadFile sendForApproved(InterestUploadFile request) throws Exception {
		if (request == null || request.getInterests() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		try {
			request.setReferenceCode(TransactionCodeGenerator.generate());
			for (Interest interest : request.getInterests()) {
				interest.setReferenceCode(TransactionCodeGenerator.generate());
				if (interest.getInterestParameters() != null) {
					for (InterestParameter interestParameter : interest.getInterestParameters()) {
						interestParameter.setInterest(interest);
					}
				}
				interest.setInterestUploadFile(request);
				interest.setCreatedBy(request.getCreatedBy());
				interest.setCreatedDate(request.getCreatedDate());
			}
			request.setWorkflowStatus(Status.SEND_FOR_APPROVE);
			// truong hop co gan Assign
			if (request.getAssignee() != null) {
				request.setAssignedDate(new Date());
				request.setWorkflowStatus(Status.ASSIGNED);
				for (Interest interest : request.getInterests()) {
					interest.setAssignee(request.getAssignee());
					interest.setAssignedDate(new Date());
				}
			}

			request = interestUploadFileRepository.save(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return request;
	}

	@Transactional
	public InterestUploadFile assign(InterestUploadFile request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestUploadFileStatus(request, Status.ASSIGNED);
	}

	@Transactional
	public InterestUploadFile returnInterestUploadFile(InterestUploadFile request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestUploadFileStatus(request, Status.SEND_FOR_APPROVE);
	}

	@Transactional
	public InterestUploadFile rejectedInterestUploadFile(InterestUploadFile request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestUploadFileStatus(request, Status.REJECTED);
	}

	@Transactional(rollbackFor = Exception.class)
	public InterestUploadFile approvedInterestUploadFile(InterestUploadFile request) throws Exception {
		InterestUploadFile primaryInterestUploadFile = new InterestUploadFile();
		List<Interest> primaryInterests = new ArrayList<Interest>();
		Interest primaryInterest;
		String restUrl = apiGatewayUrl + "/tellerapp/param/api/interestuploadfile/approved/createinprimary";
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		try {
			if (request.getInterests() != null) {
				for (Interest interest : request.getInterests()) {

					// Tìm Interest chung version có ngày approved lớn nhất
					// Tìm trước xong mới Approve, chớ không là tìm ra chính
					// thằng
					// Interest
					// hiện tại
					Interest originalInterest = interestRepository
							.findTop1ByProductAndAreaAndBranchAndCurrencyAndTimeRateAndEffectDateAndWorkflowStatusOrderByApprovedDateDesc(
									interest.getProduct(), interest.getArea(), interest.getBranch(),
									interest.getCurrency(), interest.getTimeRate(), interest.getEffectDate(),
									Status.APPROVED);

					// Approve Interest hiện tại
					interest.setApprovedBy(request.getApprovedBy());
					interest.setApprovedDate(new Date());
					interest.setWorkflowStatus(Status.APPROVED);

					// Nếu trước đó có Interest chung version đã approve thì
					// hoán
					// đổi vị
					// trí
					// 2 cái
					if (originalInterest != null && interest != null) {
						interest = swapInterest(originalInterest, interest);
					}

					primaryInterest = new Interest();
					BeanUtils.copyProperties(interest, primaryInterest);
					if (interest.getOriginalId() != null) {
						primaryInterest.setId(interest.getOriginalId());
						primaryInterest.setOriginalId(interest.getId());
					}
					primaryInterests.add(primaryInterest);
				}
			}
			request.setApprovedDate(new Date());
			request.setWorkflowStatus(Status.APPROVED);

			if (request.getComments() != null && request.getComments().size() > 0) {
				for (Comment comment : request.getComments()) {
					comment.setWorkFlowModel(request);
				}
			}
			request = interestUploadFileRepository.save(request);
			BeanUtils.copyProperties(request, primaryInterestUploadFile);

			primaryInterestUploadFile.setInterests(new HashSet<Interest>(primaryInterests));

			RestResponse<InterestUploadFile> restResponse = vn.fpt.dbp.vccb.core.rest.interest.InterestUploadFileService
					.cud(restUrl, primaryInterestUploadFile);
			if (restResponse.getStatus() != 0) {
				throw new Exception(restResponse.getErrorMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return request;
	}

	@Transactional(rollbackFor = Exception.class)
	public InterestUploadFile approveInPrimary(InterestUploadFile request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}
		try {

			// set data comment
			request = interestUploadFileRepository.save(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return request;
	}

	protected InterestUploadFile updateInterestUploadFileStatus(InterestUploadFile request, String status)
			throws Exception {
		InterestUploadFile updateInterestUploadFile = interestUploadFileRepository.findOne(request.getId());
		// xoa cac comment cu
		if (updateInterestUploadFile.getComments() != null && updateInterestUploadFile.getComments().size() > 0) {
			for (Comment comment : updateInterestUploadFile.getComments()) {
				comment.setWorkFlowModel(null);
			}
			updateInterestUploadFile.getComments().clear();
		}
		// set data comment
		if (request.getComments() != null && request.getComments().size() > 0) {
			for (Comment comment : request.getComments()) {
				comment.setWorkFlowModel(updateInterestUploadFile);
			}
			updateInterestUploadFile.setComments(request.getComments());
		}

		try {
			if (Status.SEND_FOR_APPROVE.equalsIgnoreCase(status)) {
				// Tra duyet thi khong can sinh Code
				if (!StringUtils.isNotEmpty(request.getReferenceCode())) {
					updateInterestUploadFile.setReferenceCode(TransactionCodeGenerator.generate());
					for (Interest interest : updateInterestUploadFile.getInterests()) {
						interest.setReferenceCode(TransactionCodeGenerator.generate());
					}
				}
				// Tra duyet thi set lai gia tri
				if (Status.ASSIGNED.equalsIgnoreCase(updateInterestUploadFile.getWorkflowStatus())) {
					updateInterestUploadFile.setAssignee(null);
					updateInterestUploadFile.setAssignedDate(null);
					for (Interest interest : updateInterestUploadFile.getInterests()) {
						interest.setAssignee(null);
						interest.setAssignedDate(null);
					}
				} else {
					// Gui duyet & co gan Assignee
					if (request.getAssignee() != null) {
						status = Status.ASSIGNED;
					}
				}
				if (request.getAssignGroup() != null) {
					updateInterestUploadFile.setAssignGroup(request.getAssignGroup());
				}
				if (request.getTaskId() != null) {
					updateInterestUploadFile.setTaskId(request.getTaskId());
				}
				if (request.getProcessInstanceId() != null) {
					updateInterestUploadFile.setProcessInstanceId(request.getProcessInstanceId());
				}
			}
			if (Status.ASSIGNED.equalsIgnoreCase(status)) {
				updateInterestUploadFile.setAssignee(request.getAssignee());
				updateInterestUploadFile.setAssignedDate(new Date());
				for (Interest interest : updateInterestUploadFile.getInterests()) {
					interest.setAssignee(request.getAssignee());
					interest.setAssignedDate(new Date());
				}
			}
			if (Status.REJECTED.equalsIgnoreCase(status)) {
				updateInterestUploadFile.setApprovedBy(request.getAssignee());
				updateInterestUploadFile.setApprovedDate(new Date());
				for (Interest interest : updateInterestUploadFile.getInterests()) {
					interest.setApprovedBy(request.getApprovedBy());
					interest.setApprovedDate(new Date());
					interest.setWorkflowStatus(Status.REJECTED);
				}
			}
			if (Status.APPROVED.equalsIgnoreCase(status)) {
				updateInterestUploadFile.setApprovedBy(request.getApprovedBy());
				updateInterestUploadFile.setApprovedDate(new Date());
				for (Interest interest : updateInterestUploadFile.getInterests()) {
					interest.setApprovedBy(request.getApprovedBy());
					interest.setApprovedDate(new Date());
					interest.setWorkflowStatus(Status.APPROVED);
				}
			}
			updateInterestUploadFile.setWorkflowStatus(status);
			updateInterestUploadFile = interestUploadFileRepository.save(updateInterestUploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return updateInterestUploadFile;
	}

	@Transactional
	public InterestUploadFile updateWorkFlowInfo(InterestUploadFile request) throws Exception {
		InterestUploadFile updateInterestUploadFile = request.getId() == null ? null
				: interestUploadFileRepository.findOne(request.getId());
		try {
			if (updateInterestUploadFile != null) {
				if (request.getTaskId() != null) {
					updateInterestUploadFile.setTaskId(request.getTaskId());
				}
				if (request.getProcessInstanceId() != null) {
					updateInterestUploadFile.setProcessInstanceId(request.getProcessInstanceId());
				}
			} else {
				throw new Exception(DBPException.RECORD_NOT_EXISTED);
			}
			updateInterestUploadFile = interestUploadFileRepository.save(updateInterestUploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return updateInterestUploadFile;
	}

	/**
	 * 1. Cap nhat 2 doi tuong thanh blank <br>
	 * 2. Cap nhat id cua doi tuong cu thanh id cua doi tuong moi<br>
	 * 3. Luu thong tin doi tuong cu
	 * 
	 * @param oldInterest
	 * @param newInterest
	 * @return doi tuong moi voi ID cua doi tuong cu
	 */
	private Interest swapInterest(Interest oldInterest, Interest newInterest) throws Exception {
		try {
			// Khoi tao, cap nhat null cho 2 interest
			Interest tmpInterest = new Interest();
			tmpInterest.setId(newInterest.getId());
			interestRepository.save(tmpInterest);
			tmpInterest.setId(oldInterest.getId());
			interestRepository.save(tmpInterest);

			// Set id cua oldInterest thanh new
			BeanUtils.copyProperties(oldInterest, tmpInterest);
			tmpInterest.setId(newInterest.getId());
			if (tmpInterest.getInterestParameters() != null) {
				for (InterestParameter interestParameter : tmpInterest.getInterestParameters()) {
					interestParameter.setInterest(tmpInterest);
				}
			}
			if (tmpInterest.getComments() != null) {
				for (Comment comment : tmpInterest.getComments()) {
					comment.setWorkFlowModel(tmpInterest);
				}
			}
			interestRepository.save(tmpInterest);

			newInterest.setId(oldInterest.getId());
			if (newInterest.getInterestParameters() != null) {
				for (InterestParameter interestParameter : newInterest.getInterestParameters()) {
					interestParameter.setInterest(newInterest);
				}
			}
			if (newInterest.getComments() != null) {
				for (Comment comment : newInterest.getComments()) {
					comment.setWorkFlowModel(newInterest);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return newInterest;

	}

}
