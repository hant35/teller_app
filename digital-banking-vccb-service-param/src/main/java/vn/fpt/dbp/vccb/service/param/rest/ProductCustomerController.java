package vn.fpt.dbp.vccb.service.param.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.product.ProductCustomer;
import vn.fpt.dbp.vccb.core.domain.product.QProductCustomer;
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
public class ProductCustomerController{
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    protected ProductCustomerRepository productCustomerRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/productcustomer/list", method = RequestMethod.GET, produces = "application/json")
    public List<ProductCustomer> listProductCustomer(Principal principle) {
        return productCustomerRepository.findAll();
    }

    @RequestMapping(value = "/api/productcustomer/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductCustomer productcustomerInfo(Principal principle, @PathVariable("id") Long id) {
        ProductCustomer ProductCustomer = productCustomerRepository.findOne(id);

        return ProductCustomer;
    }

    @RequestMapping(value = "/api/productcustomer/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductCustomer> createProductCustomer(Principal principle, @RequestBody @Valid ProductCustomer request) {
        RestResponse<ProductCustomer> resp = new RestResponse<ProductCustomer>();
        try {
            ProductCustomer productCustomer = request.getId() == null ? null : productCustomerRepository.findOne(request.getId());
            if (productCustomer != null) {
                resp.setStatus(RestResponse.STATUS_ERROR);
                resp.setErrorMessage("This record is exist");
                resp.setContent(request);
            }
            else {
                productCustomerRepository.save(request);
                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage("");
                resp.setContent(request);

//				ProductCustomerCreatedEvent event = new ProductCustomerCreatedEvent(request);
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

    @RequestMapping(value = "/api/productcustomer/modify", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductCustomer> modifyProductCustomer(Principal principle, @RequestBody @Valid ProductCustomer request) {
        RestResponse<ProductCustomer> resp = new RestResponse<ProductCustomer>();
        try {
            NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
            ProductCustomer productCustomer = productCustomerRepository.findOne(request.getId());
            beanUtils.copyProperties(productCustomer, request);

            productCustomerRepository.save(productCustomer);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(productCustomer);

//			ProductCustomerModifiedEvent event = new ProductCustomerModifiedEvent(productCustomer);
//			eventBus.publish(asEventMessage(event));

        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @RequestMapping(value = "/api/productcustomer/delete", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductCustomer> deleteProductCustomer(Principal principle, @RequestBody @Valid ProductCustomer request) {
        RestResponse<ProductCustomer> resp = new RestResponse<ProductCustomer>();
        try {
            ProductCustomer deletedProductCustomer = productCustomerRepository.findOne(request.getId());

            productCustomerRepository.save(deletedProductCustomer);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(deletedProductCustomer);

//			ProductCustomerDeletedEvent event = new ProductCustomerDeletedEvent(deletedProductCustomer);
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
