package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Country;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CountryService extends ClientService {
	public static Country read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Country> responseTypeRef = new ParameterizedTypeReference<Country>() {
			};
			ResponseEntity<Country> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Country> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Country>> responseTypeRef = new ParameterizedTypeReference<List<Country>>() {
			};
			ResponseEntity<List<Country>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Country> body = (List<Country>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Country> cud(String restUrl, Country model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Country>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Country>>() {
			};

			HttpEntity<Country> httpEntity = new HttpEntity<Country>(model);
			ResponseEntity<RestResponse<Country>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Country> search(String restUrl, Country model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Country>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Country>>() {
			};

			HttpEntity<Country> httpEntity = new HttpEntity<Country>(model);
			ResponseEntity<PagedResource<Country>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
