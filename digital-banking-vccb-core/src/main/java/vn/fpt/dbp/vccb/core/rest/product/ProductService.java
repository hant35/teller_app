package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ProductService extends ClientService {
	public static Product read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Product> responseTypeRef = new ParameterizedTypeReference<Product>() {
			};
			ResponseEntity<Product> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Product> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Product>> responseTypeRef = new ParameterizedTypeReference<List<Product>>() {
			};
			ResponseEntity<List<Product>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Product> body = (List<Product>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Product> cud(String restUrl, Product model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Product>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Product>>() {
			};

			HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
			ResponseEntity<RestResponse<Product>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Product> search(String restUrl, Product model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Product>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Product>>() {
			};

			HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
			ResponseEntity<PagedResource<Product>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
