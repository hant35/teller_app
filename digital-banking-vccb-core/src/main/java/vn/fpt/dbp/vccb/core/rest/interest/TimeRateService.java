package vn.fpt.dbp.vccb.core.rest.interest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class TimeRateService extends ClientService {
	public static TimeRate read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<TimeRate> responseTypeRef = new ParameterizedTypeReference<TimeRate>() {
			};
			ResponseEntity<TimeRate> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<TimeRate> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<TimeRate>> responseTypeRef = new ParameterizedTypeReference<List<TimeRate>>() {
			};
			ResponseEntity<List<TimeRate>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<TimeRate> body = (List<TimeRate>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<TimeRate> cud(String restUrl, TimeRate model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<TimeRate>> responseTypeRef = new ParameterizedTypeReference<RestResponse<TimeRate>>() {
			};

			HttpEntity<TimeRate> httpEntity = new HttpEntity<TimeRate>(model);
			ResponseEntity<RestResponse<TimeRate>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<TimeRate> search(String restUrl, TimeRate model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<TimeRate>> responseTypeRef = new ParameterizedTypeReference<PagedResource<TimeRate>>() {
			};

			HttpEntity<TimeRate> httpEntity = new HttpEntity<TimeRate>(model);
			ResponseEntity<PagedResource<TimeRate>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<TimeRate> searchByProduct(String restUrl, Product model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<TimeRate>> responseTypeRef = new ParameterizedTypeReference<PagedResource<TimeRate>>() {
			};

			HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
			ResponseEntity<PagedResource<TimeRate>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
