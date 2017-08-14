package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.AccountRefPerson;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountRefPersonService extends ClientService {
	public static AccountRefPerson read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountRefPerson> responseTypeRef = new ParameterizedTypeReference<AccountRefPerson>() {
			};
			ResponseEntity<AccountRefPerson> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountRefPerson> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountRefPerson>> responseTypeRef = new ParameterizedTypeReference<List<AccountRefPerson>>() {
			};
			ResponseEntity<List<AccountRefPerson>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<AccountRefPerson> body = (List<AccountRefPerson>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountRefPerson> cud(String restUrl, AccountRefPerson model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountRefPerson>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountRefPerson>>() {
			};

			HttpEntity<AccountRefPerson> httpEntity = new HttpEntity<AccountRefPerson>(model);
			ResponseEntity<RestResponse<AccountRefPerson>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountRefPerson> search(String restUrl, AccountRefPerson model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountRefPerson>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountRefPerson>>() {
			};

			HttpEntity<AccountRefPerson> httpEntity = new HttpEntity<AccountRefPerson>(model);
			ResponseEntity<PagedResource<AccountRefPerson>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
