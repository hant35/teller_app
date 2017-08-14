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

public class ExchangeDetailService extends ClientService {
	public static ExchangeDetail read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ExchangeDetail> responseTypeRef = new ParameterizedTypeReference<ExchangeDetail>() {
			};
			ResponseEntity<ExchangeDetail> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ExchangeDetail> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ExchangeDetail>> responseTypeRef = new ParameterizedTypeReference<List<ExchangeDetail>>() {
			};
			ResponseEntity<List<ExchangeDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<ExchangeDetail> body = (List<ExchangeDetail>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ExchangeDetail> cud(String restUrl, ExchangeDetail model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ExchangeDetail>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ExchangeDetail>>() {
			};

			HttpEntity<ExchangeDetail> httpEntity = new HttpEntity<ExchangeDetail>(model);
			ResponseEntity<RestResponse<ExchangeDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ExchangeDetail> search(String restUrl, ExchangeDetail model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ExchangeDetail>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ExchangeDetail>>() {
			};

			HttpEntity<ExchangeDetail> httpEntity = new HttpEntity<ExchangeDetail>(model);
			ResponseEntity<PagedResource<ExchangeDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ExchangeDetail> searchOSB(String restUrl, Exchange model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ExchangeDetail>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ExchangeDetail>>() {
			};

			HttpEntity<Exchange> httpEntity = new HttpEntity<Exchange>(model);
			ResponseEntity<PagedResource<ExchangeDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
