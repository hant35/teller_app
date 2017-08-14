package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountBlockadeRaise;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountBlockadeRaiseService extends ClientService {
	public static AccountBlockadeRaise read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountBlockadeRaise> responseTypeRef = new ParameterizedTypeReference<AccountBlockadeRaise>() {
			};
			ResponseEntity<AccountBlockadeRaise> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountBlockadeRaise> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountBlockadeRaise>> responseTypeRef = new ParameterizedTypeReference<List<AccountBlockadeRaise>>() {
			};
			ResponseEntity<List<AccountBlockadeRaise>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<AccountBlockadeRaise> body = (List<AccountBlockadeRaise>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountBlockadeRaise> cud(String restUrl, AccountBlockadeRaise model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountBlockadeRaise>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountBlockadeRaise>>() {
			};

			HttpEntity<AccountBlockadeRaise> httpEntity = new HttpEntity<AccountBlockadeRaise>(model);
			ResponseEntity<RestResponse<AccountBlockadeRaise>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountBlockadeRaise> search(String restUrl, AccountBlockadeRaise model)
			throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountBlockadeRaise>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountBlockadeRaise>>() {
			};

			HttpEntity<AccountBlockadeRaise> httpEntity = new HttpEntity<AccountBlockadeRaise>(model);
			ResponseEntity<PagedResource<AccountBlockadeRaise>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
