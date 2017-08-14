package vn.fpt.dbp.vccb.core.rest.customer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import java.util.List;

public class CustomerService extends ClientService{
    public static Customer read(String restUrl) throws Exception {
        try {
            ParameterizedTypeReference<Customer> responseTypeRef = new ParameterizedTypeReference<Customer>() {
            };
            ResponseEntity<Customer> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static List<Customer> findList(String restUrl) throws Exception {
        try {
            ParameterizedTypeReference<List<Customer>> responseTypeRef = new ParameterizedTypeReference<List<Customer>>() {
            };
            ResponseEntity<List<Customer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
            List<Customer> body = (List<Customer>) responseEntity.getBody();
            return body;
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static RestResponse<Customer> cud(String restUrl, Customer model) throws Exception {
        try {
            ParameterizedTypeReference<RestResponse<Customer>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Customer>>() {
            };

            HttpEntity<Customer> httpEntity = new HttpEntity<Customer>(model);
            ResponseEntity<RestResponse<Customer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static PagedResource<Customer> search(String restUrl, Customer model) throws Exception {
        try {
            ParameterizedTypeReference<PagedResource<Customer>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Customer>>() {
            };

            HttpEntity<Customer> httpEntity = new HttpEntity<Customer>(model);
            ResponseEntity<PagedResource<Customer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }
}
