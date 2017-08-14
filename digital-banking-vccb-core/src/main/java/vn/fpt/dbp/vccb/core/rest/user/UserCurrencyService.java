package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.UserCurrency;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class UserCurrencyService extends ClientService {
	public static UserCurrency read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<UserCurrency> responseTypeRef = new ParameterizedTypeReference<UserCurrency>() {
			};
			ResponseEntity<UserCurrency> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<UserCurrency> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<UserCurrency>> responseTypeRef = new ParameterizedTypeReference<List<UserCurrency>>() {
			};
			ResponseEntity<List<UserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<UserCurrency> body = (List<UserCurrency>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<UserCurrency> cud(String restUrl, UserCurrency model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<UserCurrency>> responseTypeRef = new ParameterizedTypeReference<RestResponse<UserCurrency>>() {
			};

			HttpEntity<UserCurrency> httpEntity = new HttpEntity<UserCurrency>(model);
			ResponseEntity<RestResponse<UserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<UserCurrency> search(String restUrl, UserCurrency model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<UserCurrency>> responseTypeRef = new ParameterizedTypeReference<PagedResource<UserCurrency>>() {
			};

			HttpEntity<UserCurrency> httpEntity = new HttpEntity<UserCurrency>(model);
			ResponseEntity<PagedResource<UserCurrency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
