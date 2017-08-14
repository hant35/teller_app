package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserProduct;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RestrictUserProductService extends ClientService {
	public static RestrictUserProduct read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RestrictUserProduct> responseTypeRef = new ParameterizedTypeReference<RestrictUserProduct>() {
			};
			ResponseEntity<RestrictUserProduct> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RestrictUserProduct> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RestrictUserProduct>> responseTypeRef = new ParameterizedTypeReference<List<RestrictUserProduct>>() {
			};
			ResponseEntity<List<RestrictUserProduct>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RestrictUserProduct> body = (List<RestrictUserProduct>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RestrictUserProduct> cud(String restUrl, RestrictUserProduct model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RestrictUserProduct>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RestrictUserProduct>>() {
			};

			HttpEntity<RestrictUserProduct> httpEntity = new HttpEntity<RestrictUserProduct>(model);
			ResponseEntity<RestResponse<RestrictUserProduct>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RestrictUserProduct> search(String restUrl, RestrictUserProduct model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RestrictUserProduct>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RestrictUserProduct>>() {
			};

			HttpEntity<RestrictUserProduct> httpEntity = new HttpEntity<RestrictUserProduct>(model);
			ResponseEntity<PagedResource<RestrictUserProduct>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
