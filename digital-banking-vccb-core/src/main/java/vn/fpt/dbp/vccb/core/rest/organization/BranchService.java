package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class BranchService extends ClientService {
	public static Branch read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Branch> responseTypeRef = new ParameterizedTypeReference<Branch>() {
			};
			ResponseEntity<Branch> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Branch> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Branch>> responseTypeRef = new ParameterizedTypeReference<List<Branch>>() {
			};
			ResponseEntity<List<Branch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Branch> body = (List<Branch>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Branch> cud(String restUrl, Branch model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Branch>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Branch>>() {
			};

			HttpEntity<Branch> httpEntity = new HttpEntity<Branch>(model);
			ResponseEntity<RestResponse<Branch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Branch> search(String restUrl, Branch model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Branch>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Branch>>() {
			};

			HttpEntity<Branch> httpEntity = new HttpEntity<Branch>(model);
			ResponseEntity<PagedResource<Branch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
