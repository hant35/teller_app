package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.BAccount;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class BAccountService extends ClientService {
	public static BAccount read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<BAccount> responseTypeRef = new ParameterizedTypeReference<BAccount>() {
			};
			ResponseEntity<BAccount> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<BAccount> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<BAccount>> responseTypeRef = new ParameterizedTypeReference<List<BAccount>>() {
			};
			ResponseEntity<List<BAccount>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<BAccount> body = (List<BAccount>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<BAccount> cud(String restUrl, BAccount model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<BAccount>> responseTypeRef = new ParameterizedTypeReference<RestResponse<BAccount>>() {
			};

			HttpEntity<BAccount> httpEntity = new HttpEntity<BAccount>(model);
			ResponseEntity<RestResponse<BAccount>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<BAccount> search(String restUrl, BAccount model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<BAccount>> responseTypeRef = new ParameterizedTypeReference<PagedResource<BAccount>>() {
			};

			HttpEntity<BAccount> httpEntity = new HttpEntity<BAccount>(model);
			ResponseEntity<PagedResource<BAccount>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
