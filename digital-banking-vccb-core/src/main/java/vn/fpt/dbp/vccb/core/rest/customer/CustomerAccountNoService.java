package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerAccountNo;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerAccountNoService extends ClientService {
	public static CustomerAccountNo read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerAccountNo> responseTypeRef = new ParameterizedTypeReference<CustomerAccountNo>() {
			};
			ResponseEntity<CustomerAccountNo> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerAccountNo> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerAccountNo>> responseTypeRef = new ParameterizedTypeReference<List<CustomerAccountNo>>() {
			};
			ResponseEntity<List<CustomerAccountNo>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<CustomerAccountNo> body = (List<CustomerAccountNo>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerAccountNo> cud(String restUrl, CustomerAccountNo model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerAccountNo>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerAccountNo>>() {
			};

			HttpEntity<CustomerAccountNo> httpEntity = new HttpEntity<CustomerAccountNo>(model);
			ResponseEntity<RestResponse<CustomerAccountNo>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerAccountNo> search(String restUrl, CustomerAccountNo model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerAccountNo>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerAccountNo>>() {
			};

			HttpEntity<CustomerAccountNo> httpEntity = new HttpEntity<CustomerAccountNo>(model);
			ResponseEntity<PagedResource<CustomerAccountNo>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
