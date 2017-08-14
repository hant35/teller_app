package vn.fpt.dbp.vccb.core.rest.currency;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.currency.Vault;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class VaultService extends ClientService {
	public static Vault read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Vault> responseTypeRef = new ParameterizedTypeReference<Vault>() {
			};
			ResponseEntity<Vault> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Vault> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Vault>> responseTypeRef = new ParameterizedTypeReference<List<Vault>>() {
			};
			ResponseEntity<List<Vault>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Vault> body = (List<Vault>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Vault> cud(String restUrl, Vault model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Vault>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Vault>>() {
			};

			HttpEntity<Vault> httpEntity = new HttpEntity<Vault>(model);
			ResponseEntity<RestResponse<Vault>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Vault> search(String restUrl, Vault model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Vault>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Vault>>() {
			};

			HttpEntity<Vault> httpEntity = new HttpEntity<Vault>(model);
			ResponseEntity<PagedResource<Vault>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
	
}
