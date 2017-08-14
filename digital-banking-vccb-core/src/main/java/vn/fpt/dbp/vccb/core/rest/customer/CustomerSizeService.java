package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerSize;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerSizeService extends ClientService {
	public static CustomerSize read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerSize> responseTypeRef = new ParameterizedTypeReference<CustomerSize>() {
			};
			ResponseEntity<CustomerSize> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerSize> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerSize>> responseTypeRef = new ParameterizedTypeReference<List<CustomerSize>>() {
			};
			ResponseEntity<List<CustomerSize>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<CustomerSize> body = (List<CustomerSize>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerSize> cud(String restUrl, CustomerSize model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerSize>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerSize>>() {
			};

			HttpEntity<CustomerSize> httpEntity = new HttpEntity<CustomerSize>(model);
			ResponseEntity<RestResponse<CustomerSize>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerSize> search(String restUrl, CustomerSize model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerSize>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerSize>>() {
			};

			HttpEntity<CustomerSize> httpEntity = new HttpEntity<CustomerSize>(model);
			ResponseEntity<PagedResource<CustomerSize>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
