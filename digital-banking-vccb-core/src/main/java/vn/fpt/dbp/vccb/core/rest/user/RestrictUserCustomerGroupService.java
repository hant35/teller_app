package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserCustomerGroup;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RestrictUserCustomerGroupService extends ClientService {
	public static RestrictUserCustomerGroup read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RestrictUserCustomerGroup> responseTypeRef = new ParameterizedTypeReference<RestrictUserCustomerGroup>() {
			};
			ResponseEntity<RestrictUserCustomerGroup> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RestrictUserCustomerGroup> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RestrictUserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<List<RestrictUserCustomerGroup>>() {
			};
			ResponseEntity<List<RestrictUserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RestrictUserCustomerGroup> body = (List<RestrictUserCustomerGroup>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RestrictUserCustomerGroup> cud(String restUrl, RestrictUserCustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RestrictUserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RestrictUserCustomerGroup>>() {
			};

			HttpEntity<RestrictUserCustomerGroup> httpEntity = new HttpEntity<RestrictUserCustomerGroup>(model);
			ResponseEntity<RestResponse<RestrictUserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RestrictUserCustomerGroup> search(String restUrl, RestrictUserCustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RestrictUserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RestrictUserCustomerGroup>>() {
			};

			HttpEntity<RestrictUserCustomerGroup> httpEntity = new HttpEntity<RestrictUserCustomerGroup>(model);
			ResponseEntity<PagedResource<RestrictUserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
