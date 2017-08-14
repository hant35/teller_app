package vn.fpt.dbp.vccb.core.rest.system;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.system.Function;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class FunctionService extends ClientService {
	public static Function read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Function> responseTypeRef = new ParameterizedTypeReference<Function>() {
			};
			ResponseEntity<Function> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Function> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Function>> responseTypeRef = new ParameterizedTypeReference<List<Function>>() {
			};
			ResponseEntity<List<Function>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Function> body = (List<Function>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Function> cud(String restUrl, Function model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Function>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Function>>() {
			};

			HttpEntity<Function> httpEntity = new HttpEntity<Function>(model);
			ResponseEntity<RestResponse<Function>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Function> search(String restUrl, Function model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Function>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Function>>() {
			};

			HttpEntity<Function> httpEntity = new HttpEntity<Function>(model);
			ResponseEntity<PagedResource<Function>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
