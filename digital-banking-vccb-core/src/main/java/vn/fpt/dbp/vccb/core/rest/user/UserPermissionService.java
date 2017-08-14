package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.UserPermission;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class UserPermissionService extends ClientService {
	public static UserPermission read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<UserPermission> responseTypeRef = new ParameterizedTypeReference<UserPermission>() {
			};
			ResponseEntity<UserPermission> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<UserPermission> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<UserPermission>> responseTypeRef = new ParameterizedTypeReference<List<UserPermission>>() {
			};
			ResponseEntity<List<UserPermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<UserPermission> body = (List<UserPermission>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<UserPermission> cud(String restUrl, UserPermission model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<UserPermission>> responseTypeRef = new ParameterizedTypeReference<RestResponse<UserPermission>>() {
			};

			HttpEntity<UserPermission> httpEntity = new HttpEntity<UserPermission>(model);
			ResponseEntity<RestResponse<UserPermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<UserPermission> search(String restUrl, UserPermission model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<UserPermission>> responseTypeRef = new ParameterizedTypeReference<PagedResource<UserPermission>>() {
			};

			HttpEntity<UserPermission> httpEntity = new HttpEntity<UserPermission>(model);
			ResponseEntity<PagedResource<UserPermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
