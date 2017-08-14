package vn.fpt.dbp.vccb.service.cif.rest;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.domain.customer.QCustomer;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.cif.service.CustomerService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerController {

    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private boolean isPrimary() {
        return "digital-banking-vccb-service-cif".equals(serviceName);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/list", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> list(Principal principle) {
        return customerRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/find", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<Customer> find(Principal principle, @RequestBody Customer customer, Pageable pageable){
        customer.setCreatedBy(customerService.getUser(principle.getName()));
        try {
            return customerRepository.searchLastedVersion(customer, pageable);
        } catch (Exception e) {
            logger.error("/api/customer/find: " + e.getMessage());
            return new PagedResource<Customer>(new ArrayList<Customer>(), pageable.getPageNumber(),pageable.getPageSize(),0);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<Customer> search(Principal principle, @RequestBody Customer customer, Pageable pageable){
        QCustomer qCustomer = QCustomer.customer;
        BooleanExpression booleanExpression = qCustomer.id.isNotNull();

        if (StringUtils.isNotEmpty(customer.getCode())) {
            booleanExpression = booleanExpression.and(qCustomer.code.toUpperCase().like(customer.getCode().toUpperCase()));
        }
        if(customer.getCustomerType() != null ){
            booleanExpression = booleanExpression.and(qCustomer.customerType.eq(customer.getCustomerType()));
        }
        if (StringUtils.isNotEmpty(customer.getAccountNumber())) {
            booleanExpression = booleanExpression.and(qCustomer.accountNumber.toUpperCase().like(customer.getAccountNumber().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(customer.getShortName())) {
            booleanExpression = booleanExpression.and(qCustomer.shortName.toUpperCase().like(customer.getShortName().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(customer.getFullName())) {
            booleanExpression = booleanExpression.and(qCustomer.fullName.toUpperCase().like(customer.getFullName().toUpperCase()));
        }
        if(customer.getBranch() != null ){
            booleanExpression = booleanExpression.and(qCustomer.branch.eq(customer.getBranch()));
        }
        if (StringUtils.isNotEmpty(customer.getWorkflowStatus())){
            booleanExpression = booleanExpression.and(qCustomer.workflowStatus.toUpperCase().like((customer.getWorkflowStatus().toUpperCase())));
        }
        //CMND
        if(StringUtils.isNotEmpty(customer.getLegalDocsNumber())){
            booleanExpression = booleanExpression.and(qCustomer.legalDocsNumber.toUpperCase().like(customer.getLegalDocsNumber().toUpperCase()));
        }
        if (customer.getLegalDocsType() != null){
            booleanExpression = booleanExpression.and(qCustomer.legalDocsType.eq(customer.getLegalDocsType()));
        }
        //HC
        if(StringUtils.isNotEmpty(customer.getLegalDocsNumber2())){
            booleanExpression = booleanExpression.and(qCustomer.legalDocsNumber2.toUpperCase().like(customer.getLegalDocsNumber2().toUpperCase()));
        }
        if (customer.getLegalDocsType2() != null){
            booleanExpression = booleanExpression.and(qCustomer.legalDocsType2.eq(customer.getLegalDocsType2()));
        }

        if (StringUtils.isNotEmpty(customer.getStatus())){
            booleanExpression = booleanExpression.and(qCustomer.status.toUpperCase().like((customer.getStatus().toUpperCase())));
        }
        //--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
        if (customer.getProcessInstanceId() != null) {
            booleanExpression = booleanExpression.and(qCustomer.processInstanceId.eq(customer.getProcessInstanceId()));
        }
        if (customer.getTaskId() != null) {
            booleanExpression = booleanExpression.and(qCustomer.taskId.eq(customer.getTaskId()));
        }

        Page<Customer> result = customerRepository.findAll(booleanExpression, pageable);

        return new PagedResource<Customer>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(), result.getTotalElements());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public Customer customerInfo(Principal principle, @PathVariable("id") Long id) {
        Customer customer = customerRepository.findOne(id);

        return customer;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/code/{code}", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> customerInfoByCode(Principal principle, @PathVariable("code") String code) {
        QCustomer qCustomer = QCustomer.customer;
        List<Customer> customers = (List<Customer>)  customerRepository.findAll(qCustomer.code.toUpperCase().eq(code.toUpperCase()));

        return customers;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/fullname/{fullName}", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> customerInfoByFullName(Principal principle, @PathVariable("fullName") String fullName) {
//        return customerRepository.findByFullName(fullName);
        return null;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/getapprovallist/individual", method = RequestMethod.GET, produces = "application/json")
    public List<User> getIndividualUserApprovals(Principal principle) {
        QUser quser = QUser.user;
        List<User> users = new ArrayList<User>();
        List<User> approvalList = new ArrayList<User>();

        User user = customerService.getUser(principle.getName());

        if (user != null){
            users = (List<User>) userRepository.findAll(quser.branch.eq(user.getBranch()));
        }

        for(User u : users){
//			System.out.println("--------checking user = " + u.getUsername());
            if(u.hasPermission("CIF_I", Permissions.APPROVE)){
                approvalList.add(u);
            }
        }

        return approvalList;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/getapprovallist/corporation", method = RequestMethod.GET, produces = "application/json")
    public List<User> getCorporationUserApprovals(Principal principle) {
        QUser quser = QUser.user;
        List<User> users = new ArrayList<User>();
        List<User> approvalList = new ArrayList<User>();

        User user = customerService.getUser(principle.getName());

        if (user != null){
            users = (List<User>) userRepository.findAll(quser.branch.eq(user.getBranch()));
        }

        for(User u : users){
//			System.out.println("--------checking user = " + u.getUsername());
            if(u.hasPermission("CIF_C", Permissions.APPROVE)){
                approvalList.add(u);
            }
        }

        return approvalList;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/getapprovallist/bank", method = RequestMethod.GET, produces = "application/json")
    public List<User> getBankUserApprovals(Principal principle) {
        QUser quser = QUser.user;
        List<User> users = new ArrayList<User>();
        List<User> approvalList = new ArrayList<User>();

        User user = customerService.getUser(principle.getName());

        if (user != null){
            users = (List<User>) userRepository.findAll(quser.branch.eq(user.getBranch()));
        }

        for(User u : users){
//			System.out.println("--------checking user = " + u.getUsername());
            if(u.hasPermission("CIF_B", Permissions.APPROVE)){
                approvalList.add(u);
            }
        }

        return approvalList;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/savedraft/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createSaveDraft(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try {
            request.setCreatedDate(new Date());
            request.setCreatedBy(customerService.getUser(principle.getName()));

            Customer customer = customerService.saveAsDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(customer);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createSendForApproved(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try {
            request.setCreatedDate(new Date());
            request.setCreatedBy(customerService.getUser(principle.getName()));
            request.setReferenceCode(TransactionCodeGenerator.generate());
            if(request.getAssignee() == null) {
                Customer customer = customerService.sendForApprove(request);

                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage("");
                resp.setContent(customer);
            }
            //Có chỉ định người duyệt
            else {
                String restUrl = apiGatewayUrl + "/tellerapp/cif_his/api/customer/assigned/create";
                resp = vn.fpt.dbp.vccb.core.rest.customer.CustomerService.cud(restUrl, request);
            }
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/assigned/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createAssigned(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try {
            request.setAssignedDate(new Date());
            //Không chỉ định người duyệt
            if (request.getAssignee() == null) {
                request.setAssignee(customerService.getUser(principle.getName()));
            }
            Customer customer = customerService.assign(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(customer);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/assigned/return", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> returnAssigned(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try {
            request.setAssignedDate(null);

            Customer customer = customerService.returnAssign(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(customer);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/approved/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createApproved(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = null;
        try {
            //1. set approval date
            request.setApprovedDate(new Date());
            request.setApprovedBy(customerService.getUser(principle.getName()));

            //2. process
            if (isPrimary()) {
                String restUrl = apiGatewayUrl + "/tellerapp/cif_his/api/customer/approved/create";
                resp = vn.fpt.dbp.vccb.core.rest.customer.CustomerService.cud(restUrl, request);
            } else {
                Customer customer = customerService.approve(request);
                resp = new RestResponse<Customer>();
                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage(null);
                resp.setContent(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp = new RestResponse<Customer>();
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createApprovedInPrimary(Principal principle, @RequestBody @Valid Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        if (!isPrimary()) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage("This method cannot be called directly from history service");
            resp.setContent(null);
            return resp;
        }

        try {
//			System.out.println("Run service /api/customer/approved/createinprimary");
            Customer customer = customerService.approveInPrimary(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage(null);
            resp.setContent(customer);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/rejected/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> createRejected(Principal principle, @RequestBody Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try{
            request.setApprovedDate(new Date());
            request.setApprovedBy(customerService.getUser(principle.getName()));

            Customer customer = customerService.reject(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(customer);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/customer/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<Customer> deleteSaveDraft(Principal principle, @RequestBody Customer request) {
        RestResponse<Customer> resp = new RestResponse<Customer>();
        try{

            Customer customer = customerService.deleteSaveDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(customer);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;

    }

}
