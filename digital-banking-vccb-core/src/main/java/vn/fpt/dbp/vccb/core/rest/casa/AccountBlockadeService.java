package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountBlockade;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountBlockadeService extends ClientService {
	public static AccountBlockade read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountBlockade> responseTypeRef = new ParameterizedTypeReference<AccountBlockade>() {
			};
			ResponseEntity<AccountBlockade> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountBlockade> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountBlockade>> responseTypeRef = new ParameterizedTypeReference<List<AccountBlockade>>() {
			};
			ResponseEntity<List<AccountBlockade>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<AccountBlockade> body = (List<AccountBlockade>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountBlockade> cud(String restUrl, AccountBlockade model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountBlockade>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountBlockade>>() {
			};

			HttpEntity<AccountBlockade> httpEntity = new HttpEntity<AccountBlockade>(model);
			ResponseEntity<RestResponse<AccountBlockade>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountBlockade> search(String restUrl, AccountBlockade model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountBlockade>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountBlockade>>() {
			};

			HttpEntity<AccountBlockade> httpEntity = new HttpEntity<AccountBlockade>(model);
			ResponseEntity<PagedResource<AccountBlockade>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
