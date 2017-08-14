package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.Promotion;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class PromotionService extends ClientService {
	public static Promotion read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Promotion> responseTypeRef = new ParameterizedTypeReference<Promotion>() {
			};
			ResponseEntity<Promotion> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Promotion> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Promotion>> responseTypeRef = new ParameterizedTypeReference<List<Promotion>>() {
			};
			ResponseEntity<List<Promotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Promotion> body = (List<Promotion>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Promotion> cud(String restUrl, Promotion model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Promotion>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Promotion>>() {
			};

			HttpEntity<Promotion> httpEntity = new HttpEntity<Promotion>(model);
			ResponseEntity<RestResponse<Promotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Promotion> search(String restUrl, Promotion model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Promotion>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Promotion>>() {
			};

			HttpEntity<Promotion> httpEntity = new HttpEntity<Promotion>(model);
			ResponseEntity<PagedResource<Promotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
