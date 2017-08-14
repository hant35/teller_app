package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.UserRole;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class UserRoleService extends ClientService {
	public static UserRole read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<UserRole> responseTypeRef = new ParameterizedTypeReference<UserRole>() {
			};
			ResponseEntity<UserRole> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<UserRole> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<UserRole>> responseTypeRef = new ParameterizedTypeReference<List<UserRole>>() {
			};
			ResponseEntity<List<UserRole>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<UserRole> body = (List<UserRole>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<UserRole> cud(String restUrl, UserRole model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<UserRole>> responseTypeRef = new ParameterizedTypeReference<RestResponse<UserRole>>() {
			};

			HttpEntity<UserRole> httpEntity = new HttpEntity<UserRole>(model);
			ResponseEntity<RestResponse<UserRole>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<UserRole> search(String restUrl, UserRole model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<UserRole>> responseTypeRef = new ParameterizedTypeReference<PagedResource<UserRole>>() {
			};

			HttpEntity<UserRole> httpEntity = new HttpEntity<UserRole>(model);
			ResponseEntity<PagedResource<UserRole>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
