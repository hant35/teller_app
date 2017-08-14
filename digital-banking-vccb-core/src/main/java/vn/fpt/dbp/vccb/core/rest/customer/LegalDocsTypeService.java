package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.LegalDocsType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class LegalDocsTypeService extends ClientService {
	public static LegalDocsType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<LegalDocsType> responseTypeRef = new ParameterizedTypeReference<LegalDocsType>() {
			};
			ResponseEntity<LegalDocsType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<LegalDocsType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<LegalDocsType>> responseTypeRef = new ParameterizedTypeReference<List<LegalDocsType>>() {
			};
			ResponseEntity<List<LegalDocsType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<LegalDocsType> body = (List<LegalDocsType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<LegalDocsType> cud(String restUrl, LegalDocsType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<LegalDocsType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<LegalDocsType>>() {
			};

			HttpEntity<LegalDocsType> httpEntity = new HttpEntity<LegalDocsType>(model);
			ResponseEntity<RestResponse<LegalDocsType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<LegalDocsType> search(String restUrl, LegalDocsType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<LegalDocsType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<LegalDocsType>>() {
			};

			HttpEntity<LegalDocsType> httpEntity = new HttpEntity<LegalDocsType>(model);
			ResponseEntity<PagedResource<LegalDocsType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
