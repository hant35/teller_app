package vn.fpt.dbp.vccb.service.param.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;
import vn.fpt.dbp.vccb.core.domain.interest.QInterest;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestParameterRepository;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.util.rest.RestResponse;

@Service
public class InterestService {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private InterestParameterRepository interestParameterRepository;

	@Transactional(rollbackFor = Exception.class)
	public Interest saveAsDraft(Interest request) throws Exception {
		Interest interest = request.getId() == null ? null : interestRepository.findOne(request.getId());
		try {
			if (interest != null) {
				if (!Status.SAVE_DRAFT.equalsIgnoreCase(interest.getWorkflowStatus())) {
					throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
				}
				if (interest.getInterestParameters() != null) {
					for (InterestParameter interestParameter : interest.getInterestParameters()) {
						interestParameter.setInterest(null);
						interestParameterRepository.delete(interestParameter);
					}
					interest.getInterestParameters().clear();
				}
				BeanUtils.copyProperties(request, interest);
			} else {
				interest = request;
			}
			if (interest.getInterestParameters() != null) {
				for (InterestParameter interestParameter : interest.getInterestParameters()) {
					interestParameter.setInterest(interest);
				}
			}
			// if (interest.getInterestUploadFile() != null) {
			// InterestUploadFile interestUploadFile =
			// interest.getInterestUploadFile();
			// if (interestUploadFile.getId() == null) {
			// interestUploadFile =
			// interestUploadFileRepository.save(interestUploadFile);
			// interest.setInterestUploadFile(interestUploadFile);
			// }
			// }
			interest.setCreatedDate(new Date());
			interest.setWorkflowStatus(Status.SAVE_DRAFT);
			interest = interestRepository.save(interest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return interest;
	}

	public Interest detailInterest(String id) throws Exception {
		Interest interest = new Interest(id);
		interest = interestRepository.findOne(interest.getId());

		if (interest == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		return interest;
	}

	@Transactional(rollbackFor = Exception.class)
	public Interest deleteSaveDraft(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		Interest deletedInterest = interestRepository.findOne(request.getId());
		if (deletedInterest == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}

		try {
			if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedInterest.getWorkflowStatus())) {
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
			if (deletedInterest.getInterestParameters() != null) {
				for (InterestParameter interestParameter : deletedInterest.getInterestParameters()) {
					interestParameter.setInterest(null);
					interestParameterRepository.delete(interestParameter);
				}
			}
			deletedInterest.getInterestParameters().clear();
			interestRepository.delete(deletedInterest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deletedInterest;
	}

	@Transactional
	public Interest sendForApproved(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestStatus(request, Status.SEND_FOR_APPROVE);
	}

	@Transactional
	public Interest assign(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestStatus(request, Status.ASSIGNED);
	}

	@Transactional
	public Interest returnInterest(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestStatus(request, Status.SEND_FOR_APPROVE);
	}

	@Transactional
	public Interest rejectedInterest(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getAssignee() == null || request.getAssignee().getId() == null) {
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		return updateInterestStatus(request, Status.REJECTED);
	}

	@Transactional(rollbackFor = Exception.class)
	public Interest approvedInterest(Interest request) throws Exception {
		Interest primaryInterest = new Interest();
		String restUrl = apiGatewayUrl + "/tellerapp/param/api/interest/approved/createinprimary";
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}
		if (!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())) {
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}

		// Tìm Interest chung version có ngày approved lớn nhất
		// Tìm trước xong mới Approve, chớ không là tìm ra chính thằng Interest
		// hiện tại
		Interest originalInterest = interestRepository
				.findTop1ByProductAndAreaAndBranchAndCurrencyAndTimeRateAndEffectDateAndWorkflowStatusOrderByApprovedDateDesc(
						request.getProduct(), request.getArea(), request.getBranch(), request.getCurrency(),
						request.getTimeRate(), request.getEffectDate(), Status.APPROVED);

		if(request.getOriginalId() == null){
			request.setOriginalId(request.getId());
		}
		// Approve Interest hiện tại
		Interest approvedInterest = updateInterestStatus(request, Status.APPROVED);

		// Nếu trước đó có Interest chung version đã approve thì hoán đổi vị trí
		// 2 cái
		if (originalInterest != null && approvedInterest != null) {
			Long newId = approvedInterest.getId();
			Long originalId = originalInterest.getId();
			swapInterest(newId, originalId);
			// Sau khi swap thi can Get lai approveLimit voi Id của originalId
			approvedInterest = interestRepository.findOne(originalId);
		}

		// Chuẩn bị chuyển qua DB chính
		BeanUtils.copyProperties(approvedInterest, primaryInterest);

		if (approvedInterest.getOriginalId() != null) {
			primaryInterest.setId(approvedInterest.getOriginalId());
			primaryInterest.setOriginalId(approvedInterest.getId());
		}
		RestResponse<Interest> restResponse = vn.fpt.dbp.vccb.core.rest.interest.InterestService.cud(restUrl,
				primaryInterest);
		if (restResponse.getStatus() != 0) {
			throw new Exception(restResponse.getErrorMessage());
		}
		return approvedInterest;
	}

	@Transactional(rollbackFor = Exception.class)
	public Interest approveInPrimary(Interest request) throws Exception {
		if (request == null || request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
			throw new Exception(DBPException.NO_APPROVER);
		}
		Interest interest = request.getId() == null ? null : interestRepository.findOne(request.getId());
		try {
			if (interest != null) {
				if (interest.getInterestParameters() != null) {
					for (InterestParameter interestParameter : interest.getInterestParameters()) {
						interestParameter.setInterest(null);
						interestParameterRepository.delete(interestParameter);
					}
					interest.getInterestParameters().clear();
				}
				// xoa cac comment cu
				for (Comment comment : interest.getComments()) {
					comment.setWorkFlowModel(null);
				}
				interest.getComments().clear();

				BeanUtils.copyProperties(request, interest);
			} else {
				interest = request;
			}
			if (interest.getInterestParameters() != null) {
				for (InterestParameter interestParameter : interest.getInterestParameters()) {
					interestParameter.setInterest(interest);
				}
			}
			// if (interest.getInterestUploadFile() != null) {
			// InterestUploadFile interestUploadFile =
			// interest.getInterestUploadFile();
			// if (interestUploadFile.getId() != null) {
			// InterestUploadFile in =
			// interestUploadFileRepository.findOne(interestUploadFile.getId());
			// if (in == null) {
			// interestUploadFile =
			// interestUploadFileRepository.save(interestUploadFile);
			// interest.setInterestUploadFile(interestUploadFile);
			// }
			// }
			// }
			// set data comment
			if (interest.getComments() != null && interest.getComments().size() > 0) {
				for (Comment comment : interest.getComments()) {
					comment.setWorkFlowModel(interest);
				}
			}
			interest = interestRepository.save(interest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return interest;
	}

	protected Interest updateInterestStatus(Interest interest, String status) throws Exception {
		Interest updateInterest = interestRepository.findOne(interest.getId());
		// xoa cac comment cu
		if (updateInterest.getComments() != null && updateInterest.getComments().size() > 0) {
			for (Comment comment : updateInterest.getComments()) {
				comment.setWorkFlowModel(null);
			}
			updateInterest.getComments().clear();
		}
		// set data comment
		if (interest.getComments() != null && interest.getComments().size() > 0) {
			for (Comment comment : interest.getComments()) {
				comment.setWorkFlowModel(updateInterest);
			}
			updateInterest.setComments(interest.getComments());
		}

		try {
			if (Status.SEND_FOR_APPROVE.equalsIgnoreCase(status)) {
				// Tra duyet thi khong can sinh Code
				if (!StringUtils.isNotEmpty(interest.getReferenceCode())) {
					updateInterest.setReferenceCode(TransactionCodeGenerator.generate());
				}
				// Tra duyet thi set lai gia tri
				if (Status.ASSIGNED.equalsIgnoreCase(updateInterest.getWorkflowStatus())) {
					updateInterest.setAssignee(null);
					updateInterest.setAssignedDate(null);
				} else {
					// Gui duyet & co gan Assignee
					if (interest.getAssignee() != null) {
						status = Status.ASSIGNED;
					}
				}
				if (interest.getAssignGroup() != null) {
					updateInterest.setAssignGroup(interest.getAssignGroup());
				}
				if (interest.getTaskId() != null) {
					updateInterest.setTaskId(interest.getTaskId());
				}
				if (interest.getProcessInstanceId() != null) {
					updateInterest.setProcessInstanceId(interest.getProcessInstanceId());
				}
			}
			if (Status.ASSIGNED.equalsIgnoreCase(status)) {
				updateInterest.setAssignee(interest.getAssignee());
				updateInterest.setAssignedDate(new Date());
			}
			if (Status.APPROVED.equalsIgnoreCase(status)) {
				if (interest.getOriginalId() == null) {
					updateInterest.setOriginalId(interest.getId());
				}
				updateInterest.setApprovedBy(interest.getApprovedBy());
				updateInterest.setApprovedDate(new Date());
			}
			if (Status.REJECTED.equalsIgnoreCase(status)) {
				updateInterest.setApprovedBy(interest.getApprovedBy());
				updateInterest.setApprovedDate(new Date());
			}
			updateInterest.setWorkflowStatus(status);
			updateInterest = interestRepository.save(updateInterest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return updateInterest;
	}

	@Transactional
	public Interest updateWorkFlowInfo(Interest request) throws Exception {
		Interest updateInterest = request.getId() == null ? null : interestRepository.findOne(request.getId());
		try {
			if (updateInterest != null) {
				if (request.getTaskId() != null) {
					updateInterest.setTaskId(request.getTaskId());
				}
				if (request.getProcessInstanceId() != null) {
					updateInterest.setProcessInstanceId(request.getProcessInstanceId());
				}
			} else {
				throw new Exception(DBPException.RECORD_NOT_EXISTED);
			}
			updateInterest = interestRepository.save(updateInterest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return updateInterest;
	}

	public BooleanExpression buildCommonQuery(BooleanExpression booleanExpression, Interest interest) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression expr = booleanExpression;

		if (interest == null) {
			return expr;
		}

		if (StringUtils.isNotEmpty(interest.getWorkflowStatus())) {
			expr = expr.and(qInterest.workflowStatus.like(interest.getWorkflowStatus()));
		}

		if (interest.getProduct() != null) {
			expr = expr.and(qInterest.product.eq(interest.getProduct()));
		}

		if (interest.getCurrency() != null) {
			expr = expr.and(qInterest.currency.eq(interest.getCurrency()));
		}

		if (interest.getTimeRate() != null) {
			expr = expr.and(qInterest.timeRate.eq(interest.getTimeRate()));
		}

		if (interest.getArea() != null) {
			expr = expr.and(qInterest.area.eq(interest.getArea()));
		}

		if (interest.getBranch() != null) {
			expr = expr.and(qInterest.branch.eq(interest.getBranch()));
		}

		if (interest.getEffectDate() != null) {
			// Set to YYYY-MM-DD 00:00:00
			Date dateFrom = DateUtils.ceiling(interest.getEffectDate(), Calendar.DATE);
			// Set to YYYY-MM-(DD+1) 00:00:00
			Date dateTo = DateUtils.addDays(dateFrom, 1);
			// YYYY-MM-DD 00:00:00 <= effectDate < YYYY-MM-(DD+1) 00:00:00
			expr = expr.and(qInterest.effectDate.goe(dateFrom)).and(qInterest.effectDate.lt(dateTo));
		}

		if (interest.getInterestUploadFile() != null) {
			expr = expr.and(qInterest.interestUploadFile.fileName.toUpperCase()
					.like(interest.getInterestUploadFile().getFileName().toUpperCase()));
		}

		if (interest.getProcessInstanceId() != null) {
			expr = expr.and(qInterest.processInstanceId.eq(interest.getProcessInstanceId()));
		}

		if (interest.getTaskId() != null) {
			expr = expr.and(qInterest.taskId.eq(interest.getTaskId()));
		}

		return expr;
	}

	private void swapInterest(Long firstId, Long secondId) throws Exception {
		Interest firstInterest, secondInterest;
		Interest tempFirstInterest, tempSecondInterest, emptyInterest;

		try {
			firstInterest = interestRepository.findOne(firstId);
			secondInterest = interestRepository.findOne(secondId);

			tempFirstInterest = new Interest();
			tempSecondInterest = new Interest();
			emptyInterest = new Interest();

			// Nếu 1 trong 2 cái null thì swap bằng niềm tin
			if (firstInterest != null && secondInterest != null) {
				BeanUtils.copyProperties(firstInterest, tempFirstInterest);
				BeanUtils.copyProperties(secondInterest, tempSecondInterest);
				// Để cập nhật Interest thứ nhất về empty
				emptyInterest.setId(firstId);

				// Chuyển ID của interest thứ nhất thành ID của Interest thứ
				// hai(bao gồm luôn cả con nó)
				tempFirstInterest.setId(secondId);
				// interestParameters
				if (tempFirstInterest.getInterestParameters() != null) {
					for (InterestParameter interestParameterFirst : tempFirstInterest.getInterestParameters()) {
						interestParameterFirst.setInterest(tempFirstInterest);
					}
				}
				// Comment
				if (tempFirstInterest.getComments() != null) {
					for (Comment commentFirst : tempFirstInterest.getComments()) {
						commentFirst.setWorkFlowModel(tempFirstInterest);
					}
				}

				// Chuyển ID của interest thứ hai thành ID của Interest thứ
				// nhất(bao gồm luôn cả con nó)
				tempSecondInterest.setId(firstId);
				// interestParameters
				if (tempSecondInterest.getInterestParameters() != null) {
					for (InterestParameter interestParameterSecond : tempSecondInterest.getInterestParameters()) {
						interestParameterSecond.setInterest(tempSecondInterest);
					}
				}
				// Comment
				if (tempSecondInterest.getComments() != null) {
					for (Comment commentSecond : tempSecondInterest.getComments()) {
						commentSecond.setWorkFlowModel(tempSecondInterest);
					}
				}

				// Lưu lại (và hưởng thụ thành quả)
				interestRepository.save(emptyInterest);
				interestRepository.save(tempFirstInterest);
				interestRepository.save(tempSecondInterest);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
