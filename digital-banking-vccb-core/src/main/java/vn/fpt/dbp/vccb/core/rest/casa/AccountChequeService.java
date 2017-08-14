package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountCheque;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountChequeService extends ClientService {
	public static AccountCheque read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountCheque> responseTypeRef = new ParameterizedTypeReference<AccountCheque>() {
			};
			ResponseEntity<AccountCheque> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountCheque> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountCheque>> responseTypeRef = new ParameterizedTypeReference<List<AccountCheque>>() {
			};
			ResponseEntity<List<AccountCheque>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<AccountCheque> body = (List<AccountCheque>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountCheque> cud(String restUrl, AccountCheque model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountCheque>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountCheque>>() {
			};

			HttpEntity<AccountCheque> httpEntity = new HttpEntity<AccountCheque>(model);
			ResponseEntity<RestResponse<AccountCheque>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountCheque> search(String restUrl, AccountCheque model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountCheque>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountCheque>>() {
			};

			HttpEntity<AccountCheque> httpEntity = new HttpEntity<AccountCheque>(model);
			ResponseEntity<PagedResource<AccountCheque>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
