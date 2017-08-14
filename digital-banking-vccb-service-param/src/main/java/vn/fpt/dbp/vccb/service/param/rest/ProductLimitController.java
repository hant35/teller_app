package vn.fpt.dbp.vccb.service.param.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.product.ProductCustomer;
import vn.fpt.dbp.vccb.core.domain.product.ProductLimit;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductCustomerRepository;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductLimitRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
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
public class ProductLimitController {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    protected ProductLimitRepository productLimitRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/productlimit/list", method = RequestMethod.GET, produces = "application/json")
    public List<ProductLimit> listProductLimit(Principal principle) {
        return productLimitRepository.findAll();
    }

    @RequestMapping(value = "/api/productlimit/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductLimit productLimitInfo(Principal principle, @PathVariable("id") Long id) {
        ProductLimit ProductLimit = productLimitRepository.findOne(id);

        return ProductLimit;
    }

    @RequestMapping(value = "/api/productlimit/create", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductLimit> createProductLimit(Principal principle, @RequestBody @Valid ProductLimit request) {
        RestResponse<ProductLimit> resp = new RestResponse<ProductLimit>();
        try {
            ProductLimit productLimit = request.getId() == null ? null : productLimitRepository.findOne(request.getId());
            if (productLimit != null) {
                resp.setStatus(RestResponse.STATUS_ERROR);
                resp.setErrorMessage("This record is exist");
                resp.setContent(request);
            }
            else {
                productLimitRepository.save(request);
                resp.setStatus(RestResponse.STATUS_SUCCESS);
                resp.setErrorMessage("");
                resp.setContent(request);

//				ProductLimitCreatedEvent event = new ProductLimitCreatedEvent(request);
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

    @RequestMapping(value = "/api/productlimit/modify", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductLimit> modifyProductLimit(Principal principle, @RequestBody @Valid ProductLimit request) {
        RestResponse<ProductLimit> resp = new RestResponse<ProductLimit>();
        try {
            NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
            ProductLimit productLimit = productLimitRepository.findOne(request.getId());
            beanUtils.copyProperties(productLimit, request);

            productLimitRepository.save(productLimit);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(productLimit);

//			ProductLimitModifiedEvent event = new ProductLimitModifiedEvent(productLimit);
//			eventBus.publish(asEventMessage(event));

        }
        catch (Exception e) {
            resp.setStatus(RestResponse.STATUS_ERROR);
            resp.setErrorMessage(e.getLocalizedMessage());
            resp.setContent(null);
        }
        return resp;
    }

    @RequestMapping(value = "/api/productlimit/delete", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<ProductLimit> deleteProductLimit(Principal principle, @RequestBody @Valid ProductLimit request) {
        RestResponse<ProductLimit> resp = new RestResponse<ProductLimit>();
        try {
            ProductLimit deletedProductLimit = productLimitRepository.findOne(request.getId());

            productLimitRepository.save(deletedProductLimit);
            resp.setStatus(RestResponse.STATUS_SUCCESS);
            resp.setErrorMessage("");
            resp.setContent(deletedProductLimit);

//			ProductLimitDeletedEvent event = new ProductLimitDeletedEvent(deletedProductLimit);
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
