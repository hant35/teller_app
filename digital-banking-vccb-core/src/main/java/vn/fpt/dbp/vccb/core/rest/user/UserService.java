package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class UserService extends ClientService {
	public static User read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<User> responseTypeRef = new ParameterizedTypeReference<User>() {
			};
			ResponseEntity<User> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<User> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<User>> responseTypeRef = new ParameterizedTypeReference<List<User>>() {
			};
			ResponseEntity<List<User>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<User> body = (List<User>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<User> cud(String restUrl, User model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<User>> responseTypeRef = new ParameterizedTypeReference<RestResponse<User>>() {
			};

			HttpEntity<User> httpEntity = new HttpEntity<User>(model);
			ResponseEntity<RestResponse<User>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<User> search(String restUrl, User model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<User>> responseTypeRef = new ParameterizedTypeReference<PagedResource<User>>() {
			};

			HttpEntity<User> httpEntity = new HttpEntity<User>(model);
			ResponseEntity<PagedResource<User>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
