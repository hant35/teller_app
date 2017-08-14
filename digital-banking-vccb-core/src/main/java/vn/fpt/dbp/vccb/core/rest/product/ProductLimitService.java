package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.ProductLimit;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ProductLimitService extends ClientService {
	public static ProductLimit read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ProductLimit> responseTypeRef = new ParameterizedTypeReference<ProductLimit>() {
			};
			ResponseEntity<ProductLimit> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ProductLimit> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ProductLimit>> responseTypeRef = new ParameterizedTypeReference<List<ProductLimit>>() {
			};
			ResponseEntity<List<ProductLimit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<ProductLimit> body = (List<ProductLimit>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ProductLimit> cud(String restUrl, ProductLimit model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ProductLimit>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ProductLimit>>() {
			};

			HttpEntity<ProductLimit> httpEntity = new HttpEntity<ProductLimit>(model);
			ResponseEntity<RestResponse<ProductLimit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ProductLimit> search(String restUrl, ProductLimit model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ProductLimit>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ProductLimit>>() {
			};

			HttpEntity<ProductLimit> httpEntity = new HttpEntity<ProductLimit>(model);
			ResponseEntity<PagedResource<ProductLimit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
