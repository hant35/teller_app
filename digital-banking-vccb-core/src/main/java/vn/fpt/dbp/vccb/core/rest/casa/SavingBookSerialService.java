package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.SavingBookSerial;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class SavingBookSerialService extends ClientService {
	public static SavingBookSerial read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<SavingBookSerial> responseTypeRef = new ParameterizedTypeReference<SavingBookSerial>() {
			};
			ResponseEntity<SavingBookSerial> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<SavingBookSerial> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<SavingBookSerial>> responseTypeRef = new ParameterizedTypeReference<List<SavingBookSerial>>() {
			};
			ResponseEntity<List<SavingBookSerial>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<SavingBookSerial> body = (List<SavingBookSerial>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<SavingBookSerial> cud(String restUrl, SavingBookSerial model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<SavingBookSerial>> responseTypeRef = new ParameterizedTypeReference<RestResponse<SavingBookSerial>>() {
			};

			HttpEntity<SavingBookSerial> httpEntity = new HttpEntity<SavingBookSerial>(model);
			ResponseEntity<RestResponse<SavingBookSerial>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<SavingBookSerial> search(String restUrl, SavingBookSerial model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<SavingBookSerial>> responseTypeRef = new ParameterizedTypeReference<PagedResource<SavingBookSerial>>() {
			};

			HttpEntity<SavingBookSerial> httpEntity = new HttpEntity<SavingBookSerial>(model);
			ResponseEntity<PagedResource<SavingBookSerial>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
