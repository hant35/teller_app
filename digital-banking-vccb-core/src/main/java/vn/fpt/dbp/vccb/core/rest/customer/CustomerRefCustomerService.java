package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerRefCustomer;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerRefCustomerService extends ClientService {
	public static CustomerRefCustomer read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerRefCustomer> responseTypeRef = new ParameterizedTypeReference<CustomerRefCustomer>() {
			};
			ResponseEntity<CustomerRefCustomer> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerRefCustomer> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerRefCustomer>> responseTypeRef = new ParameterizedTypeReference<List<CustomerRefCustomer>>() {
			};
			ResponseEntity<List<CustomerRefCustomer>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<CustomerRefCustomer> body = (List<CustomerRefCustomer>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerRefCustomer> cud(String restUrl, CustomerRefCustomer model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerRefCustomer>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerRefCustomer>>() {
			};

			HttpEntity<CustomerRefCustomer> httpEntity = new HttpEntity<CustomerRefCustomer>(model);
			ResponseEntity<RestResponse<CustomerRefCustomer>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerRefCustomer> search(String restUrl, CustomerRefCustomer model)
			throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerRefCustomer>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerRefCustomer>>() {
			};

			HttpEntity<CustomerRefCustomer> httpEntity = new HttpEntity<CustomerRefCustomer>(model);
			ResponseEntity<PagedResource<CustomerRefCustomer>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
