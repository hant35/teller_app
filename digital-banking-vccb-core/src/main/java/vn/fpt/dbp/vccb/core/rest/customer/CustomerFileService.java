package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerFile;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerFileService extends ClientService {
	public static CustomerFile read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerFile> responseTypeRef = new ParameterizedTypeReference<CustomerFile>() {
			};
			ResponseEntity<CustomerFile> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerFile> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerFile>> responseTypeRef = new ParameterizedTypeReference<List<CustomerFile>>() {
			};
			ResponseEntity<List<CustomerFile>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<CustomerFile> body = (List<CustomerFile>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerFile> cud(String restUrl, CustomerFile model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerFile>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerFile>>() {
			};

			HttpEntity<CustomerFile> httpEntity = new HttpEntity<CustomerFile>(model);
			ResponseEntity<RestResponse<CustomerFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerFile> search(String restUrl, CustomerFile model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerFile>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerFile>>() {
			};

			HttpEntity<CustomerFile> httpEntity = new HttpEntity<CustomerFile>(model);
			ResponseEntity<PagedResource<CustomerFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
