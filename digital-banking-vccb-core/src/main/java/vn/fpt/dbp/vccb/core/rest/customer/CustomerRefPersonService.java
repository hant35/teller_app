package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerRefPerson;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerRefPersonService extends ClientService {
	public static CustomerRefPerson read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerRefPerson> responseTypeRef = new ParameterizedTypeReference<CustomerRefPerson>() {
			};
			ResponseEntity<CustomerRefPerson> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerRefPerson> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerRefPerson>> responseTypeRef = new ParameterizedTypeReference<List<CustomerRefPerson>>() {
			};
			ResponseEntity<List<CustomerRefPerson>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<CustomerRefPerson> body = (List<CustomerRefPerson>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerRefPerson> cud(String restUrl, CustomerRefPerson model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerRefPerson>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerRefPerson>>() {
			};

			HttpEntity<CustomerRefPerson> httpEntity = new HttpEntity<CustomerRefPerson>(model);
			ResponseEntity<RestResponse<CustomerRefPerson>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerRefPerson> search(String restUrl, CustomerRefPerson model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerRefPerson>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerRefPerson>>() {
			};

			HttpEntity<CustomerRefPerson> httpEntity = new HttpEntity<CustomerRefPerson>(model);
			ResponseEntity<PagedResource<CustomerRefPerson>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
