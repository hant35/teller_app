package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.ProductPromotion;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ProductPromotionService extends ClientService {
	public static ProductPromotion read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ProductPromotion> responseTypeRef = new ParameterizedTypeReference<ProductPromotion>() {
			};
			ResponseEntity<ProductPromotion> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ProductPromotion> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ProductPromotion>> responseTypeRef = new ParameterizedTypeReference<List<ProductPromotion>>() {
			};
			ResponseEntity<List<ProductPromotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<ProductPromotion> body = (List<ProductPromotion>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ProductPromotion> cud(String restUrl, ProductPromotion model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ProductPromotion>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ProductPromotion>>() {
			};

			HttpEntity<ProductPromotion> httpEntity = new HttpEntity<ProductPromotion>(model);
			ResponseEntity<RestResponse<ProductPromotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ProductPromotion> search(String restUrl, ProductPromotion model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ProductPromotion>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ProductPromotion>>() {
			};

			HttpEntity<ProductPromotion> httpEntity = new HttpEntity<ProductPromotion>(model);
			ResponseEntity<PagedResource<ProductPromotion>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
