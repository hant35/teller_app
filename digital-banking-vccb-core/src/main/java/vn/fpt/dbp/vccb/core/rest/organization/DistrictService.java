package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.District;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class DistrictService extends ClientService {
	public static District read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<District> responseTypeRef = new ParameterizedTypeReference<District>() {
			};
			ResponseEntity<District> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<District> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<District>> responseTypeRef = new ParameterizedTypeReference<List<District>>() {
			};
			ResponseEntity<List<District>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<District> body = (List<District>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<District> cud(String restUrl, District model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<District>> responseTypeRef = new ParameterizedTypeReference<RestResponse<District>>() {
			};

			HttpEntity<District> httpEntity = new HttpEntity<District>(model);
			ResponseEntity<RestResponse<District>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<District> search(String restUrl, District model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<District>> responseTypeRef = new ParameterizedTypeReference<PagedResource<District>>() {
			};

			HttpEntity<District> httpEntity = new HttpEntity<District>(model);
			ResponseEntity<PagedResource<District>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
