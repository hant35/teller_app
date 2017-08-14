package vn.fpt.dbp.vccb.core.rest.currency;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CurrencyService extends ClientService {
	public static Currency read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Currency> responseTypeRef = new ParameterizedTypeReference<Currency>() {
			};
			ResponseEntity<Currency> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Currency> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Currency>> responseTypeRef = new ParameterizedTypeReference<List<Currency>>() {
			};
			ResponseEntity<List<Currency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Currency> body = (List<Currency>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Currency> cud(String restUrl, Currency model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Currency>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Currency>>() {
			};

			HttpEntity<Currency> httpEntity = new HttpEntity<Currency>(model);
			ResponseEntity<RestResponse<Currency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Currency> search(String restUrl, Currency model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Currency>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Currency>>() {
			};

			HttpEntity<Currency> httpEntity = new HttpEntity<Currency>(model);
			ResponseEntity<PagedResource<Currency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Currency> searchByProduct(String restUrl, Product model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Currency>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Currency>>() {
			};

			HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
			ResponseEntity<PagedResource<Currency>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
