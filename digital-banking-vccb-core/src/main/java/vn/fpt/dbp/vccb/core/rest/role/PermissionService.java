package vn.fpt.dbp.vccb.core.rest.role;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.role.RolePermission;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class PermissionService extends ClientService {
	public static RolePermission read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RolePermission> responseTypeRef = new ParameterizedTypeReference<RolePermission>() {
			};
			ResponseEntity<RolePermission> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RolePermission> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RolePermission>> responseTypeRef = new ParameterizedTypeReference<List<RolePermission>>() {
			};
			ResponseEntity<List<RolePermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RolePermission> body = (List<RolePermission>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RolePermission> cud(String restUrl, RolePermission model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RolePermission>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RolePermission>>() {
			};

			HttpEntity<RolePermission> httpEntity = new HttpEntity<RolePermission>(model);
			ResponseEntity<RestResponse<RolePermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RolePermission> search(String restUrl, RolePermission model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RolePermission>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RolePermission>>() {
			};

			HttpEntity<RolePermission> httpEntity = new HttpEntity<RolePermission>(model);
			ResponseEntity<PagedResource<RolePermission>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
