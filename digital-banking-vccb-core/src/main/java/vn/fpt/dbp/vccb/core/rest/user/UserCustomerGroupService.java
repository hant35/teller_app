package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.UserCustomerGroup;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class UserCustomerGroupService extends ClientService {
	public static UserCustomerGroup read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<UserCustomerGroup> responseTypeRef = new ParameterizedTypeReference<UserCustomerGroup>() {
			};
			ResponseEntity<UserCustomerGroup> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<UserCustomerGroup> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<UserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<List<UserCustomerGroup>>() {
			};
			ResponseEntity<List<UserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<UserCustomerGroup> body = (List<UserCustomerGroup>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<UserCustomerGroup> cud(String restUrl, UserCustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<UserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<RestResponse<UserCustomerGroup>>() {
			};

			HttpEntity<UserCustomerGroup> httpEntity = new HttpEntity<UserCustomerGroup>(model);
			ResponseEntity<RestResponse<UserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<UserCustomerGroup> search(String restUrl, UserCustomerGroup model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<UserCustomerGroup>> responseTypeRef = new ParameterizedTypeReference<PagedResource<UserCustomerGroup>>() {
			};

			HttpEntity<UserCustomerGroup> httpEntity = new HttpEntity<UserCustomerGroup>(model);
			ResponseEntity<PagedResource<UserCustomerGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
