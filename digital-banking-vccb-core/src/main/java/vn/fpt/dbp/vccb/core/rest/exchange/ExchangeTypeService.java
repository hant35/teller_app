package vn.fpt.dbp.vccb.core.rest.exchange;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ExchangeTypeService extends ClientService {
	public static ExchangeType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ExchangeType> responseTypeRef = new ParameterizedTypeReference<ExchangeType>() {
			};
			ResponseEntity<ExchangeType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ExchangeType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ExchangeType>> responseTypeRef = new ParameterizedTypeReference<List<ExchangeType>>() {
			};
			ResponseEntity<List<ExchangeType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<ExchangeType> body = (List<ExchangeType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ExchangeType> cud(String restUrl, ExchangeType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ExchangeType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ExchangeType>>() {
			};

			HttpEntity<ExchangeType> httpEntity = new HttpEntity<ExchangeType>(model);
			ResponseEntity<RestResponse<ExchangeType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ExchangeType> search(String restUrl, ExchangeType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ExchangeType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ExchangeType>>() {
			};

			HttpEntity<ExchangeType> httpEntity = new HttpEntity<ExchangeType>(model);
			ResponseEntity<PagedResource<ExchangeType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
