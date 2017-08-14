package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.City;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CityService extends ClientService {
	public static City read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<City> responseTypeRef = new ParameterizedTypeReference<City>() {
			};
			ResponseEntity<City> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<City> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<City>> responseTypeRef = new ParameterizedTypeReference<List<City>>() {
			};
			ResponseEntity<List<City>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<City> body = (List<City>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<City> cud(String restUrl, City model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<City>> responseTypeRef = new ParameterizedTypeReference<RestResponse<City>>() {
			};

			HttpEntity<City> httpEntity = new HttpEntity<City>(model);
			ResponseEntity<RestResponse<City>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<City> search(String restUrl, City model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<City>> responseTypeRef = new ParameterizedTypeReference<PagedResource<City>>() {
			};

			HttpEntity<City> httpEntity = new HttpEntity<City>(model);
			ResponseEntity<PagedResource<City>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
