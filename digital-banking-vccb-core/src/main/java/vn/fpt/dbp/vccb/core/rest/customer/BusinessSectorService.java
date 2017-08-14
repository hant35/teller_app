package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.BusinessSector;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class BusinessSectorService extends ClientService {
	public static BusinessSector read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<BusinessSector> responseTypeRef = new ParameterizedTypeReference<BusinessSector>() {
			};
			ResponseEntity<BusinessSector> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<BusinessSector> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<BusinessSector>> responseTypeRef = new ParameterizedTypeReference<List<BusinessSector>>() {
			};
			ResponseEntity<List<BusinessSector>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<BusinessSector> body = (List<BusinessSector>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<BusinessSector> cud(String restUrl, BusinessSector model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<BusinessSector>> responseTypeRef = new ParameterizedTypeReference<RestResponse<BusinessSector>>() {
			};

			HttpEntity<BusinessSector> httpEntity = new HttpEntity<BusinessSector>(model);
			ResponseEntity<RestResponse<BusinessSector>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<BusinessSector> search(String restUrl, BusinessSector model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<BusinessSector>> responseTypeRef = new ParameterizedTypeReference<PagedResource<BusinessSector>>() {
			};

			HttpEntity<BusinessSector> httpEntity = new HttpEntity<BusinessSector>(model);
			ResponseEntity<PagedResource<BusinessSector>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
