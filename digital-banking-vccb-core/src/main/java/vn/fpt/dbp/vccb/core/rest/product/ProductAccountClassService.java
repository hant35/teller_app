package vn.fpt.dbp.vccb.core.rest.product;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import java.util.List;

public class ProductAccountClassService extends ClientService {
    public static ProductAccountClass read(String restUrl) throws Exception {
        try {
            ParameterizedTypeReference<ProductAccountClass> responseTypeRef = new ParameterizedTypeReference<ProductAccountClass>() {
            };
            ResponseEntity<ProductAccountClass> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static List<ProductAccountClass> findList(String restUrl) throws Exception {
        try {
            ParameterizedTypeReference<List<ProductAccountClass>> responseTypeRef = new ParameterizedTypeReference<List<ProductAccountClass>>() {
            };
            ResponseEntity<List<ProductAccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
            List<ProductAccountClass> body = (List<ProductAccountClass>) responseEntity.getBody();
            return body;
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static RestResponse<ProductAccountClass> cud(String restUrl, ProductAccountClass model) throws Exception {
        try {
            ParameterizedTypeReference<RestResponse<ProductAccountClass>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ProductAccountClass>>() {
            };

            HttpEntity<ProductAccountClass> httpEntity = new HttpEntity<ProductAccountClass>(model);
            ResponseEntity<RestResponse<ProductAccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static PagedResource<ProductAccountClass> search(String restUrl, ProductAccountClass model) throws Exception {
        try {
            ParameterizedTypeReference<PagedResource<ProductAccountClass>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ProductAccountClass>>() {
            };

            HttpEntity<ProductAccountClass> httpEntity = new HttpEntity<ProductAccountClass>(model);
            ResponseEntity<PagedResource<ProductAccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static PagedResource<ProductAccountClass> searchOSB(String restUrl, Product model) throws Exception {
        try {
            ParameterizedTypeReference<PagedResource<ProductAccountClass>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ProductAccountClass>>() {
            };

            HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
            ResponseEntity<PagedResource<ProductAccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

}
