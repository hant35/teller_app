package vn.fpt.dbp.vccb.service.param.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.product.*;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.param.service.ProductService;
import vn.fpt.dbp.vccb.service.param.util.UserUtil;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@Component
//@EnableTransactionManagement(proxyTargetClass = true)
public class ProductController {

	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-param".equals(serviceName);
	}

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserUtil userUtil;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

//	@Secured("PRODUCT_VIEW")
//	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/list", method = RequestMethod.GET, produces = "application/json")
	public List<Product> listProducts(Principal principle) {
		return productRepository.findAll();
	}

	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Product productInfo(Principal principle, @PathVariable("id") Long id) {
		Product Product = productRepository.findOne(id);
		
		return Product;
	}

	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<Product> productInfoByCode(Principal principle, @PathVariable("code") String code) {
		QProduct qProduct = QProduct.product;
		List<Product> Product = (List<Product>)  productRepository.findAll(qProduct.code.toUpperCase().like(code.toUpperCase()));

		return Product;
	}

	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Product> productInfoByName(Principal principle, @PathVariable("name") String name) {
		return productRepository.findByNameLikeIgnoreCase(name);
	}

	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();

		String username = "";
		Long branchId;

		User user = userUtil.getUser(principle.getName());

		if (user != null){
			users = (List<User>) userRepository.findAll(quser.branch.id.eq(user.getBranch().getId()));
		}

		for(User u : users){
//			System.out.println("--------checking user = " + u.getUsername());
			if(u.hasPermission("PRODUCT", Permissions.APPROVE)){
				approvalList.add(u);
			}
		}

		return approvalList;
	}

	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Product> searchProduct(Principal principle, @RequestBody Product product, Pageable pageable) {

		QProduct qProduct = QProduct.product;
		BooleanExpression booleanExpression = qProduct.id.isNotNull();

		if (StringUtils.isNotEmpty(product.getCode())) {
			booleanExpression = booleanExpression.and(qProduct.code.toUpperCase().like(product.getCode().toUpperCase()));
		}

		if (StringUtils.isNotEmpty(product.getName())) {
			booleanExpression = booleanExpression.and(qProduct.name.toUpperCase().like(product.getName().toUpperCase()));
		}

		if (product.getDepositType() != null) {
			booleanExpression = booleanExpression.and(qProduct.depositType.id.eq(product.getDepositType().getId()));
		}

		if (product.getAgeFrom() != null && product.getAgeTo() != null) {
			booleanExpression = booleanExpression.and(qProduct.ageFrom.goe(product.getAgeFrom()));
			booleanExpression = booleanExpression.and(qProduct.ageTo.loe(product.getAgeTo()));
		}

		if (product.getProductCustomers() != null) {
			for (ProductCustomer productCustomer : product.getProductCustomers()
					) {
				if (productCustomer.getCustomerType() != null && productCustomer.getCustomerSize() == null) {
					booleanExpression = booleanExpression.and(qProduct.productCustomers.any().customerType.id.eq(productCustomer.getCustomerType().getId()));
				}
				if (productCustomer.getCustomerType() == null && productCustomer.getCustomerSize() != null) {
					booleanExpression = booleanExpression.and(qProduct.productCustomers.any().customerSize.id.eq(productCustomer.getCustomerSize().getId()));
				}
				if (productCustomer.getCustomerType() != null && productCustomer.getCustomerSize() != null) {
					booleanExpression = booleanExpression.and(qProduct.productCustomers.any().customerType.id.eq(productCustomer.getCustomerType().getId()));
					booleanExpression = booleanExpression.and(qProduct.productCustomers.any().customerSize.id.eq(productCustomer.getCustomerSize().getId()));
				}
				break;
			}
		}

		if (product.getEffectedDate() != null) {
			booleanExpression = booleanExpression.and(qProduct.effectedDate.goe(product.getEffectedDate()));
		}

		if (product.getExpiredDate() != null) {
			booleanExpression = booleanExpression.and(qProduct.expiredDate.loe(product.getExpiredDate()));
		}

		if (StringUtils.isNotEmpty(product.getWorkflowStatus())) {
			booleanExpression = booleanExpression.and(qProduct.workflowStatus.eq(product.getWorkflowStatus()));
		}

		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (product.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qProduct.processInstanceId.eq(product.getProcessInstanceId()));
		}
		if (product.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qProduct.taskId.eq(product.getTaskId()));
		}

		//Trang Thai San Pham: Dang hoat dong/ Ngung hoat dong
		//Status <<< Waiting

		if(product.getTransferable() != null) {
			booleanExpression = booleanExpression.and(qProduct.transferable.eq(product.getTransferable()));
		}

		if(product.getGender() != null) {
			booleanExpression = booleanExpression.and(qProduct.gender.like(product.getGender()));
		}

		Page<Product> result = productRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Product>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(), result.getTotalElements());
	}


	@Secured("PRODUCT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Product> findProduct(Principal principle, @RequestBody Product product, Pageable pageable){
		product.setCreatedBy(userUtil.getUser(principle.getName()));
		try {
			return productRepository.searchLastedVersion(product, pageable);
		} catch (Exception e) {
			return new PagedResource<Product>(new ArrayList<Product>(), pageable.getPageNumber(),pageable.getPageSize(),0);
		}
	}

	@Secured("PRODUCT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createSaveDraftProduct(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try {
			request.setCreatedDate(new Date());
			request.setCreatedBy(userUtil.getUser(principle.getName()));

			Product product = productService.saveAsDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(product);
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createSendForApprovedProduct(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try {
			request.setCreatedDate(new Date());
			request.setCreatedBy(userUtil.getUser(principle.getName()));
			request.setReferenceCode(TransactionCodeGenerator.generate());
			if(request.getAssignee() == null) {
				Product product = productService.sendForApprove(request);

				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(product);
			}
			//Có chỉ định người duyệt
			else {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/product/assigned/create";
				resp = vn.fpt.dbp.vccb.core.rest.product.ProductService.cud(restUrl, request);
			}
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createAssignedProduct(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try {
			request.setAssignedDate(new Date());

			Product product = productService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(product);
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> returnAssignedProduct(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try {
			request.setAssignedDate(null);

			Product product = productService.returnAssign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(product);
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createApprovedProduct(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = null;
		try {
			System.out.print("---------Start to run service /api/product/approved/create");
			//1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			//2. process
			if (isPrimary()) {
//				System.out.print("----------Goto isPrimary condition");
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/product/approved/create";
				//String restUrl = apiGatewayUrl + ":2001/api/product/approved/create";
//				System.out.print("----------With url = " + restUrl);
				resp = vn.fpt.dbp.vccb.core.rest.product.ProductService.cud(restUrl, request);
			} else {

//				System.out.print("----------Goto Non-isPrimary condition");
//				System.out.println("----------request.getApprovedBy()=" + request.getApprovedBy());

				Product product = productService.approve(request);
				resp = new RestResponse<Product>();
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp = new RestResponse<Product>();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createApprovedProductInPrimary(Principal principle, @RequestBody @Valid Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}

		try {
//			System.out.println("Run service /api/product/approved/createinprimary");
			Product product = productService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(product);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("PRODUCT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> createRejectedProduct(Principal principle, @RequestBody Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try{
			request.setApprovedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			Product product = productService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(product);
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("PRODUCT_DELETE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Product> deleteSaveDraftProduct(Principal principle, @RequestBody Product request) {
		RestResponse<Product> resp = new RestResponse<Product>();
		try{

			Product product = productService.deleteSaveDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(product);
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

}
