package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountService;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountServiceService extends ClientService {
	public static AccountService read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountService> responseTypeRef = new ParameterizedTypeReference<AccountService>() {
			};
			ResponseEntity<AccountService> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountService> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountService>> responseTypeRef = new ParameterizedTypeReference<List<AccountService>>() {
			};
			ResponseEntity<List<AccountService>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<AccountService> body = (List<AccountService>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountService> cud(String restUrl, AccountService model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountService>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountService>>() {
			};

			HttpEntity<AccountService> httpEntity = new HttpEntity<AccountService>(model);
			ResponseEntity<RestResponse<AccountService>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountService> search(String restUrl, AccountService model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountService>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountService>>() {
			};

			HttpEntity<AccountService> httpEntity = new HttpEntity<AccountService>(model);
			ResponseEntity<PagedResource<AccountService>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
