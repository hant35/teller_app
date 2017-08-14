package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Language;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class LanguageService extends ClientService {
	public static Language read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Language> responseTypeRef = new ParameterizedTypeReference<Language>() {
			};
			ResponseEntity<Language> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Language> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Language>> responseTypeRef = new ParameterizedTypeReference<List<Language>>() {
			};
			ResponseEntity<List<Language>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Language> body = (List<Language>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Language> cud(String restUrl, Language model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Language>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Language>>() {
			};

			HttpEntity<Language> httpEntity = new HttpEntity<Language>(model);
			ResponseEntity<RestResponse<Language>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Language> search(String restUrl, Language model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Language>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Language>>() {
			};

			HttpEntity<Language> httpEntity = new HttpEntity<Language>(model);
			ResponseEntity<PagedResource<Language>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
