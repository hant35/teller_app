package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserFunction;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RestrictUserFunctionService extends ClientService {
	public static RestrictUserFunction read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RestrictUserFunction> responseTypeRef = new ParameterizedTypeReference<RestrictUserFunction>() {
			};
			ResponseEntity<RestrictUserFunction> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RestrictUserFunction> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RestrictUserFunction>> responseTypeRef = new ParameterizedTypeReference<List<RestrictUserFunction>>() {
			};
			ResponseEntity<List<RestrictUserFunction>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RestrictUserFunction> body = (List<RestrictUserFunction>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RestrictUserFunction> cud(String restUrl, RestrictUserFunction model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RestrictUserFunction>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RestrictUserFunction>>() {
			};

			HttpEntity<RestrictUserFunction> httpEntity = new HttpEntity<RestrictUserFunction>(model);
			ResponseEntity<RestResponse<RestrictUserFunction>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RestrictUserFunction> search(String restUrl, RestrictUserFunction model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RestrictUserFunction>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RestrictUserFunction>>() {
			};

			HttpEntity<RestrictUserFunction> httpEntity = new HttpEntity<RestrictUserFunction>(model);
			ResponseEntity<PagedResource<RestrictUserFunction>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
