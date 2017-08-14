package vn.fpt.dbp.vccb.core.rest.interest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class InterestParameterService extends ClientService {
	public static InterestParameter read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<InterestParameter> responseTypeRef = new ParameterizedTypeReference<InterestParameter>() {
			};
			ResponseEntity<InterestParameter> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<InterestParameter> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<InterestParameter>> responseTypeRef = new ParameterizedTypeReference<List<InterestParameter>>() {
			};
			ResponseEntity<List<InterestParameter>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<InterestParameter> body = (List<InterestParameter>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<InterestParameter> cud(String restUrl, InterestParameter model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<InterestParameter>> responseTypeRef = new ParameterizedTypeReference<RestResponse<InterestParameter>>() {
			};

			HttpEntity<InterestParameter> httpEntity = new HttpEntity<InterestParameter>(model);
			ResponseEntity<RestResponse<InterestParameter>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<InterestParameter> search(String restUrl, InterestParameter model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<InterestParameter>> responseTypeRef = new ParameterizedTypeReference<PagedResource<InterestParameter>>() {
			};

			HttpEntity<InterestParameter> httpEntity = new HttpEntity<InterestParameter>(model);
			ResponseEntity<PagedResource<InterestParameter>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
