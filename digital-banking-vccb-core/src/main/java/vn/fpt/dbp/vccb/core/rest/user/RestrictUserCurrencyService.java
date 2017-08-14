package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserCurrency;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RestrictUserCurrencyService extends ClientService {
	public static RestrictUserCurrency read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RestrictUserCurrency> responseTypeRef = new ParameterizedTypeReference<RestrictUserCurrency>() {
			};
			ResponseEntity<RestrictUserCurrency> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RestrictUserCurrency> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RestrictUserCurrency>> responseTypeRef = new ParameterizedTypeReference<List<RestrictUserCurrency>>() {
			};
			ResponseEntity<List<RestrictUserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RestrictUserCurrency> body = (List<RestrictUserCurrency>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RestrictUserCurrency> cud(String restUrl, RestrictUserCurrency model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RestrictUserCurrency>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RestrictUserCurrency>>() {
			};

			HttpEntity<RestrictUserCurrency> httpEntity = new HttpEntity<RestrictUserCurrency>(model);
			ResponseEntity<RestResponse<RestrictUserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RestrictUserCurrency> search(String restUrl, RestrictUserCurrency model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RestrictUserCurrency>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RestrictUserCurrency>>() {
			};

			HttpEntity<RestrictUserCurrency> httpEntity = new HttpEntity<RestrictUserCurrency>(model);
			ResponseEntity<PagedResource<RestrictUserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
