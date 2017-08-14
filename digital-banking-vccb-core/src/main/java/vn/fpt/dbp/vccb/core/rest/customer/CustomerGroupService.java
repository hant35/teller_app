package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerGroup;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerGroupService extends ClientService {
	public static CustomerGroup read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerGroup> responseTypeRef = new ParameterizedTypeReference<CustomerGroup>() {
			};
			ResponseEntity<CustomerGroup> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerGroup> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerGroup>> responseTypeRef = new ParameterizedTypeReference<List<CustomerGroup>>() {
			};
			ResponseEntity<List<CustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<CustomerGroup> body = (List<CustomerGroup>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerGroup> cud(String restUrl, CustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerGroup>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerGroup>>() {
			};

			HttpEntity<CustomerGroup> httpEntity = new HttpEntity<CustomerGroup>(model);
			ResponseEntity<RestResponse<CustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerGroup> search(String restUrl, CustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerGroup>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerGroup>>() {
			};

			HttpEntity<CustomerGroup> httpEntity = new HttpEntity<CustomerGroup>(model);
			ResponseEntity<PagedResource<CustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
