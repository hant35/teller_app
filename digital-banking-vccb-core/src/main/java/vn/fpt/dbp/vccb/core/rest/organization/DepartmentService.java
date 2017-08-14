package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Department;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class DepartmentService extends ClientService {
	public static Department read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Department> responseTypeRef = new ParameterizedTypeReference<Department>() {
			};
			ResponseEntity<Department> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Department> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Department>> responseTypeRef = new ParameterizedTypeReference<List<Department>>() {
			};
			ResponseEntity<List<Department>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Department> body = (List<Department>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Department> cud(String restUrl, Department model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Department>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Department>>() {
			};

			HttpEntity<Department> httpEntity = new HttpEntity<Department>(model);
			ResponseEntity<RestResponse<Department>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Department> search(String restUrl, Department model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Department>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Department>>() {
			};

			HttpEntity<Department> httpEntity = new HttpEntity<Department>(model);
			ResponseEntity<PagedResource<Department>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
