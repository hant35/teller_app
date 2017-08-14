package vn.fpt.dbp.vccb.core.rest.role;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RoleService extends ClientService {
	public static Role read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Role> responseTypeRef = new ParameterizedTypeReference<Role>() {
			};
			ResponseEntity<Role> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Role> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Role>> responseTypeRef = new ParameterizedTypeReference<List<Role>>() {
			};
			ResponseEntity<List<Role>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Role> body = (List<Role>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Role> cud(String restUrl, Role model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Role>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Role>>() {
			};

			HttpEntity<Role> httpEntity = new HttpEntity<Role>(model);
			ResponseEntity<RestResponse<Role>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Role> search(String restUrl, Role model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Role>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Role>>() {
			};

			HttpEntity<Role> httpEntity = new HttpEntity<Role>(model);
			ResponseEntity<PagedResource<Role>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
