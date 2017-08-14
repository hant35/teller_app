package vn.fpt.dbp.vccb.core.rest.exchange;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeUploadFile;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ExchangeUploadFileService extends ClientService {
	public static ExchangeUploadFile read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ExchangeUploadFile> responseTypeRef = new ParameterizedTypeReference<ExchangeUploadFile>() {
			};
			ResponseEntity<ExchangeUploadFile> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ExchangeUploadFile> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ExchangeUploadFile>> responseTypeRef = new ParameterizedTypeReference<List<ExchangeUploadFile>>() {
			};
			ResponseEntity<List<ExchangeUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<ExchangeUploadFile> body = (List<ExchangeUploadFile>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ExchangeUploadFile> cud(String restUrl, ExchangeUploadFile model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ExchangeUploadFile>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ExchangeUploadFile>>() {
			};

			HttpEntity<ExchangeUploadFile> httpEntity = new HttpEntity<ExchangeUploadFile>(model);
			ResponseEntity<RestResponse<ExchangeUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ExchangeUploadFile> search(String restUrl, ExchangeUploadFile model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ExchangeUploadFile>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ExchangeUploadFile>>() {
			};

			HttpEntity<ExchangeUploadFile> httpEntity = new HttpEntity<ExchangeUploadFile>(model);
			ResponseEntity<PagedResource<ExchangeUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
