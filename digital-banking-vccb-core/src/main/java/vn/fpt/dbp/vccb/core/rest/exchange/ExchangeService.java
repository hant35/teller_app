package vn.fpt.dbp.vccb.core.rest.exchange;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeDetail;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ExchangeService extends ClientService {
	public static Exchange read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Exchange> responseTypeRef = new ParameterizedTypeReference<Exchange>() {
			};
			ResponseEntity<Exchange> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Exchange> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Exchange>> responseTypeRef = new ParameterizedTypeReference<List<Exchange>>() {
			};
			ResponseEntity<List<Exchange>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Exchange> body = (List<Exchange>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Exchange> cud(String restUrl, Exchange model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Exchange>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Exchange>>() {
			};

			HttpEntity<Exchange> httpEntity = new HttpEntity<Exchange>(model);
			ResponseEntity<RestResponse<Exchange>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Exchange> search(String restUrl, Exchange model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Exchange>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Exchange>>() {
			};

			HttpEntity<Exchange> httpEntity = new HttpEntity<Exchange>(model);
			ResponseEntity<PagedResource<Exchange>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
