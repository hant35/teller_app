package vn.fpt.dbp.vccb.service.cif.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.customer.AccountNoReserved;
import vn.fpt.dbp.vccb.core.domain.customer.QAccountNoReserved;
import vn.fpt.dbp.vccb.core.domain.customer.repository.AccountNoReservedRepository;
import vn.fpt.dbp.vccb.service.cif.service.AccountNoReservedService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class AccountNoReservedController {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    AccountNoReservedRepository accountNoReservedRepository;

    @Autowired
    AccountNoReservedService accountNoReservedService;

    private boolean isPrimary() {
        return "digital-banking-vccb-service-cif".equals(serviceName);
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountNoReservedController.class);

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/list", method = RequestMethod.GET, produces = "application/json")
    public List<AccountNoReserved> list(Principal principle) {
        return accountNoReservedRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public AccountNoReserved detail(Principal principle, @PathVariable("id") Long id) {
        AccountNoReserved accountNoReserved = accountNoReservedRepository.findOne(id);

        return accountNoReserved;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/find", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<AccountNoReserved> find(Principal principle, @RequestBody AccountNoReserved accountNoReserved, Pageable pageable){
        accountNoReserved.setCreatedBy(accountNoReservedService.getUser(principle.getName()));
        try {
            return accountNoReservedRepository.searchLastedVersion(accountNoReserved, pageable);
        } catch (Exception e) {
            logger.error("/api/accountnoreserved/find: " + e.getMessage());
            return new PagedResource<AccountNoReserved>(new ArrayList<AccountNoReserved>(), pageable.getPageNumber(),pageable.getPageSize(),0);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<AccountNoReserved> search(Principal principle, @RequestBody AccountNoReserved accountNoReserved, Pageable pageable) {
        QAccountNoReserved qAccountNoReserved = QAccountNoReserved.accountNoReserved;
        BooleanExpression booleanExpression = qAccountNoReserved.id.isNotNull();

        if (accountNoReserved.getBranch() != null) {
            booleanExpression = booleanExpression.and(qAccountNoReserved.branch.eq(accountNoReserved.getBranch()));
        }
        if (StringUtils.isNotEmpty(accountNoReserved.getCurrentNo())) {
            booleanExpression = booleanExpression.and(qAccountNoReserved.currentNo.toUpperCase().like(accountNoReserved.getCurrentNo().toUpperCase()));
        }
        if (accountNoReserved.getQuantity() != null) {
            booleanExpression = booleanExpression.and(qAccountNoReserved.quantity.eq(accountNoReserved.getQuantity()));
        }
        if (accountNoReserved.getEndNo() != null) {
            booleanExpression = booleanExpression.and(qAccountNoReserved.endNo.toUpperCase().like(accountNoReserved.getEndNo().toUpperCase()));
        }

        Page<AccountNoReserved> result = accountNoReservedRepository.findAll(booleanExpression, pageable);

        return new PagedResource<AccountNoReserved>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(), result.getTotalElements());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/currentno/{currentno}", method = RequestMethod.GET, produces = "application/json")
    public List<AccountNoReserved> accountNoReservedInfoByCurrentNo (Principal principle, @PathVariable("currentno") String currentNo) {
        QAccountNoReserved qAccountNoReserved = QAccountNoReserved.accountNoReserved;
        List<AccountNoReserved> accountNoReserveds = (List<AccountNoReserved>)  accountNoReservedRepository.findAll(qAccountNoReserved.currentNo.toUpperCase().eq(currentNo.toUpperCase()));

        return accountNoReserveds;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/savedraft/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> createSaveDraft(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try {
            request.setCreatedDate(new Date());
            request.setCreatedBy(accountNoReservedService.getUser(principle.getName()));

            AccountNoReserved accountNoReserved = accountNoReservedService.saveAsDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> sendForApprove(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try {
            request.setCreatedDate(new Date());
            request.setCreatedBy(accountNoReservedService.getUser(principle.getName()));

            AccountNoReserved accountNoReserved = accountNoReservedService.saveAsDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/assigned/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> assign(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try {
            request.setCreatedDate(new Date());
            request.setCreatedBy(accountNoReservedService.getUser(principle.getName()));

            AccountNoReserved accountNoReserved = accountNoReservedService.saveAsDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/assigned/return", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> returnAssigned(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try {
            request.setAssignedDate(null);

            AccountNoReserved accountNoReserved = accountNoReservedService.returnAssign(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/approved/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> createApproved(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = null;
        try {
            logger.info("---------Start to run service /api/accountnoreserved/approved/create");
            //1. set approval date
            request.setApprovedDate(new Date());
            request.setApprovedBy(accountNoReservedService.getUser(principle.getName()));

            //2. process
            if (isPrimary()) {
                String restUrl = apiGatewayUrl + "/tellerapp/cif_his/api/customer/approved/create";
                resp = vn.fpt.dbp.vccb.core.rest.customer.AccountNoReservedService.cud(restUrl, request);
            } else {
                AccountNoReserved accountNoReserved = accountNoReservedService.approve(request);
                resp = new RestResponse<AccountNoReserved>();
                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage(null);
                resp.setContent(accountNoReserved);
            }
        } catch (Exception e) {
            logger.error("/api/accountnoreserved/approved/create: " + e.getMessage());
            resp = new RestResponse<AccountNoReserved>();
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getMessage());
            resp.setContent(null);
        }
        return resp;
    }


    @RequestMapping(value = "/api/accountnoreserved/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> createApprovedInPrimary(Principal principle, @RequestBody @Valid AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        if (!isPrimary()) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage("This method cannot be called directly from history service");
            resp.setContent(null);
            return resp;
        }

        try {
            logger.info("Run service /api/accountnoreserved/approved/createinprimary");
            AccountNoReserved accountNoReserved = accountNoReservedService.approveInPrimary(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage(null);
            resp.setContent(accountNoReserved);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/rejected/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> createRejected(Principal principle, @RequestBody AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try{
            request.setApprovedDate(new Date());
            request.setApprovedBy(accountNoReservedService.getUser(principle.getName()));

            AccountNoReserved accountNoReserved = accountNoReservedService.reject(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/accountnoreserved/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<AccountNoReserved> deleteSaveDraft(Principal principle, @RequestBody AccountNoReserved request) {
        RestResponse<AccountNoReserved> resp = new RestResponse<AccountNoReserved>();
        try{

            AccountNoReserved accountNoReserved = accountNoReservedService.deleteSaveDraft(request);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(accountNoReserved);
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;

    }


}
