package vn.fpt.dbp.vccb.service.reference.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.product.DepositType;
import vn.fpt.dbp.vccb.core.domain.product.ProductCustomer;
import vn.fpt.dbp.vccb.core.domain.product.QDepositType;
import vn.fpt.dbp.vccb.core.domain.product.repository.DepositTypeRepository;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductCustomerRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by T450 on 4/20/2017.
 */
@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class DepositTypeController {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    protected DepositTypeRepository depositTypeRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/deposittype/list", method = RequestMethod.GET, produces = "application/json")
    public List<DepositType> listDepositType(Principal principle) {
        return depositTypeRepository.findAll();
    }

    @RequestMapping(value = "/api/deposittype/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public DepositType depositTypeInfo(Principal principle, @PathVariable("id") Long id) {
        DepositType DepositType = depositTypeRepository.findOne(id);

        return DepositType;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/deposittype/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<DepositType> searchDepositType(Principal principle, @RequestBody DepositType depositType, Pageable pageable) {
        QDepositType qDepositType = QDepositType.depositType;
        BooleanExpression booleanExpression = qDepositType.id.isNotNull();

        Page<DepositType> result = depositTypeRepository.findAll(booleanExpression, pageable);

        return new PagedResource<DepositType>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
    }

    @RequestMapping(value = "/api/deposittype/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<DepositType> createDepositType(Principal principle, @RequestBody @Valid DepositType request) {
        RestResponse<DepositType> resp = new RestResponse<DepositType>();
        try {
            DepositType depositType = request.getId() == null ? null : depositTypeRepository.findOne(request.getId());
            if (depositType != null) {
                resp.setStatus(RestResponse.STATUS_ERROR);
                resp.setErrorMessage("This record is exist");
                resp.setContent(request);
            }
            else {
                depositTypeRepository.save(request);
                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage("");
                resp.setContent(request);

//				DepositTypeCreatedEvent event = new DepositTypeCreatedEvent(request);
//				eventBus.publish(asEventMessage(event));
            }
        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @RequestMapping(value = "/api/deposittype/modify", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<DepositType> modifyDepositType(Principal principle, @RequestBody @Valid DepositType request) {
        RestResponse<DepositType> resp = new RestResponse<DepositType>();
        try {
            NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
            DepositType depositType = depositTypeRepository.findOne(request.getId());
            beanUtils.copyProperties(depositType, request);

            depositTypeRepository.save(depositType);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(depositType);

//			DepositTypeModifiedEvent event = new DepositTypeModifiedEvent(depositType);
//			eventBus.publish(asEventMessage(event));

        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @RequestMapping(value = "/api/deposittype/delete", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<DepositType> deleteDepositType(Principal principle, @RequestBody @Valid DepositType request) {
        RestResponse<DepositType> resp = new RestResponse<DepositType>();
        try {
            DepositType deletedDepositType = depositTypeRepository.findOne(request.getId());

            depositTypeRepository.save(deletedDepositType);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(deletedDepositType);

//			DepositTypeDeletedEvent event = new DepositTypeDeletedEvent(deletedDepositType);
//			eventBus.publish(asEventMessage(event));

        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

}
