package vn.fpt.dbp.vccb.core.rest.currency;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.currency.Till;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class TillService extends ClientService {
	public static Till read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Till> responseTypeRef = new ParameterizedTypeReference<Till>() {
			};
			ResponseEntity<Till> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Till> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Till>> responseTypeRef = new ParameterizedTypeReference<List<Till>>() {
			};
			ResponseEntity<List<Till>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Till> body = (List<Till>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Till> cud(String restUrl, Till model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Till>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Till>>() {
			};

			HttpEntity<Till> httpEntity = new HttpEntity<Till>(model);
			ResponseEntity<RestResponse<Till>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Till> search(String restUrl, Till model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Till>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Till>>() {
			};

			HttpEntity<Till> httpEntity = new HttpEntity<Till>(model);
			ResponseEntity<PagedResource<Till>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
