package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.AccountClass;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AccountClassService extends ClientService{
	public static AccountClass read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AccountClass> responseTypeRef = new ParameterizedTypeReference<AccountClass>() {
			};
			ResponseEntity<AccountClass> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AccountClass> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AccountClass>> responseTypeRef = new ParameterizedTypeReference<List<AccountClass>>() {
			};
			ResponseEntity<List<AccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<AccountClass> body = (List<AccountClass>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AccountClass> cud(String restUrl, AccountClass model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AccountClass>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AccountClass>>() {
			};

			HttpEntity<AccountClass> httpEntity = new HttpEntity<AccountClass>(model);
			ResponseEntity<RestResponse<AccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AccountClass> search(String restUrl, AccountClass model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AccountClass>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AccountClass>>() {
			};

			HttpEntity<AccountClass> httpEntity = new HttpEntity<AccountClass>(model);
			ResponseEntity<PagedResource<AccountClass>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
