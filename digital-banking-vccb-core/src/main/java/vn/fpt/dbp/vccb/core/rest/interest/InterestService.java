package vn.fpt.dbp.vccb.core.rest.interest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class InterestService extends ClientService {
	public static Interest read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Interest> responseTypeRef = new ParameterizedTypeReference<Interest>() {
			};
			ResponseEntity<Interest> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Interest> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Interest>> responseTypeRef = new ParameterizedTypeReference<List<Interest>>() {
			};
			ResponseEntity<List<Interest>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Interest> body = (List<Interest>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Interest> cud(String restUrl, Interest model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Interest>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Interest>>() {
			};

			HttpEntity<Interest> httpEntity = new HttpEntity<Interest>(model);
			ResponseEntity<RestResponse<Interest>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Interest> search(String restUrl, Interest model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Interest>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Interest>>() {
			};

			HttpEntity<Interest> httpEntity = new HttpEntity<Interest>(model);
			ResponseEntity<PagedResource<Interest>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<InterestParameter> listParam(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<InterestParameter>> responseTypeRef = new ParameterizedTypeReference<List<InterestParameter>>() {
			};
			ResponseEntity<List<InterestParameter>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<InterestParameter> body = (List<InterestParameter>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
