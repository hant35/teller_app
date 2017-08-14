package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.AccountNoReserved;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountNoReservedService extends ClientService {
	public static AccountNoReserved read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountNoReserved> responseTypeRef = new ParameterizedTypeReference<AccountNoReserved>() {
			};
			ResponseEntity<AccountNoReserved> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountNoReserved> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountNoReserved>> responseTypeRef = new ParameterizedTypeReference<List<AccountNoReserved>>() {
			};
			ResponseEntity<List<AccountNoReserved>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<AccountNoReserved> body = (List<AccountNoReserved>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountNoReserved> cud(String restUrl, AccountNoReserved model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountNoReserved>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountNoReserved>>() {
			};

			HttpEntity<AccountNoReserved> httpEntity = new HttpEntity<AccountNoReserved>(model);
			ResponseEntity<RestResponse<AccountNoReserved>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountNoReserved> search(String restUrl, AccountNoReserved model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountNoReserved>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountNoReserved>>() {
			};

			HttpEntity<AccountNoReserved> httpEntity = new HttpEntity<AccountNoReserved>(model);
			ResponseEntity<PagedResource<AccountNoReserved>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
