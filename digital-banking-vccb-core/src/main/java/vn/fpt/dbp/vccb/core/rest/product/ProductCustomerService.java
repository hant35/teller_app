package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.ProductCustomer;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ProductCustomerService extends ClientService {
	public static ProductCustomer read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ProductCustomer> responseTypeRef = new ParameterizedTypeReference<ProductCustomer>() {
			};
			ResponseEntity<ProductCustomer> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ProductCustomer> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ProductCustomer>> responseTypeRef = new ParameterizedTypeReference<List<ProductCustomer>>() {
			};
			ResponseEntity<List<ProductCustomer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<ProductCustomer> body = (List<ProductCustomer>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ProductCustomer> cud(String restUrl, ProductCustomer model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ProductCustomer>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ProductCustomer>>() {
			};

			HttpEntity<ProductCustomer> httpEntity = new HttpEntity<ProductCustomer>(model);
			ResponseEntity<RestResponse<ProductCustomer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ProductCustomer> search(String restUrl, ProductCustomer model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ProductCustomer>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ProductCustomer>>() {
			};

			HttpEntity<ProductCustomer> httpEntity = new HttpEntity<ProductCustomer>(model);
			ResponseEntity<PagedResource<ProductCustomer>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
