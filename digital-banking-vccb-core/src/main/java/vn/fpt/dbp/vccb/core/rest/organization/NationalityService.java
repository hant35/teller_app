package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Nationality;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class NationalityService extends ClientService {
	public static Nationality read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Nationality> responseTypeRef = new ParameterizedTypeReference<Nationality>() {
			};
			ResponseEntity<Nationality> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Nationality> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Nationality>> responseTypeRef = new ParameterizedTypeReference<List<Nationality>>() {
			};
			ResponseEntity<List<Nationality>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Nationality> body = (List<Nationality>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Nationality> cud(String restUrl, Nationality model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Nationality>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Nationality>>() {
			};

			HttpEntity<Nationality> httpEntity = new HttpEntity<Nationality>(model);
			ResponseEntity<RestResponse<Nationality>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Nationality> search(String restUrl, Nationality model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Nationality>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Nationality>>() {
			};

			HttpEntity<Nationality> httpEntity = new HttpEntity<Nationality>(model);
			ResponseEntity<PagedResource<Nationality>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
