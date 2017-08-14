package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerManager;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerManagerService extends ClientService {
	public static CustomerManager read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerManager> responseTypeRef = new ParameterizedTypeReference<CustomerManager>() {
			};
			ResponseEntity<CustomerManager> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerManager> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerManager>> responseTypeRef = new ParameterizedTypeReference<List<CustomerManager>>() {
			};
			ResponseEntity<List<CustomerManager>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<CustomerManager> body = (List<CustomerManager>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerManager> cud(String restUrl, CustomerManager model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerManager>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerManager>>() {
			};

			HttpEntity<CustomerManager> httpEntity = new HttpEntity<CustomerManager>(model);
			ResponseEntity<RestResponse<CustomerManager>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerManager> search(String restUrl, CustomerManager model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerManager>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerManager>>() {
			};

			HttpEntity<CustomerManager> httpEntity = new HttpEntity<CustomerManager>(model);
			ResponseEntity<PagedResource<CustomerManager>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
