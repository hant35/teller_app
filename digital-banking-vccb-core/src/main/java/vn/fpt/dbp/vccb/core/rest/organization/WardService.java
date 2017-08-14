package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Ward;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class WardService extends ClientService {
	public static Ward read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Ward> responseTypeRef = new ParameterizedTypeReference<Ward>() {
			};
			ResponseEntity<Ward> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Ward> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Ward>> responseTypeRef = new ParameterizedTypeReference<List<Ward>>() {
			};
			ResponseEntity<List<Ward>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Ward> body = (List<Ward>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Ward> cud(String restUrl, Ward model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Ward>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Ward>>() {
			};

			HttpEntity<Ward> httpEntity = new HttpEntity<Ward>(model);
			ResponseEntity<RestResponse<Ward>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Ward> search(String restUrl, Ward model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Ward>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Ward>>() {
			};

			HttpEntity<Ward> httpEntity = new HttpEntity<Ward>(model);
			ResponseEntity<PagedResource<Ward>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
