package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountInterest;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountInterestService extends ClientService {
	public static AccountInterest read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountInterest> responseTypeRef = new ParameterizedTypeReference<AccountInterest>() {
			};
			ResponseEntity<AccountInterest> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountInterest> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountInterest>> responseTypeRef = new ParameterizedTypeReference<List<AccountInterest>>() {
			};
			ResponseEntity<List<AccountInterest>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<AccountInterest> body = (List<AccountInterest>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountInterest> cud(String restUrl, AccountInterest model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountInterest>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountInterest>>() {
			};

			HttpEntity<AccountInterest> httpEntity = new HttpEntity<AccountInterest>(model);
			ResponseEntity<RestResponse<AccountInterest>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountInterest> search(String restUrl, AccountInterest model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountInterest>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountInterest>>() {
			};

			HttpEntity<AccountInterest> httpEntity = new HttpEntity<AccountInterest>(model);
			ResponseEntity<PagedResource<AccountInterest>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
