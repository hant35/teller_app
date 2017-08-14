package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.ChequeSerial;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ChequeSerialService extends ClientService {
	public static ChequeSerial read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ChequeSerial> responseTypeRef = new ParameterizedTypeReference<ChequeSerial>() {
			};
			ResponseEntity<ChequeSerial> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ChequeSerial> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ChequeSerial>> responseTypeRef = new ParameterizedTypeReference<List<ChequeSerial>>() {
			};
			ResponseEntity<List<ChequeSerial>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<ChequeSerial> body = (List<ChequeSerial>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ChequeSerial> cud(String restUrl, ChequeSerial model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ChequeSerial>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ChequeSerial>>() {
			};

			HttpEntity<ChequeSerial> httpEntity = new HttpEntity<ChequeSerial>(model);
			ResponseEntity<RestResponse<ChequeSerial>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ChequeSerial> search(String restUrl, ChequeSerial model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ChequeSerial>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ChequeSerial>>() {
			};

			HttpEntity<ChequeSerial> httpEntity = new HttpEntity<ChequeSerial>(model);
			ResponseEntity<PagedResource<ChequeSerial>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
