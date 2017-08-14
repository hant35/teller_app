package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.ServiceType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ServiceTypeService extends ClientService {
	public static ServiceType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ServiceType> responseTypeRef = new ParameterizedTypeReference<ServiceType>() {
			};
			ResponseEntity<ServiceType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ServiceType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ServiceType>> responseTypeRef = new ParameterizedTypeReference<List<ServiceType>>() {
			};
			ResponseEntity<List<ServiceType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<ServiceType> body = (List<ServiceType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ServiceType> cud(String restUrl, ServiceType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ServiceType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ServiceType>>() {
			};

			HttpEntity<ServiceType> httpEntity = new HttpEntity<ServiceType>(model);
			ResponseEntity<RestResponse<ServiceType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ServiceType> search(String restUrl, ServiceType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ServiceType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ServiceType>>() {
			};

			HttpEntity<ServiceType> httpEntity = new HttpEntity<ServiceType>(model);
			ResponseEntity<PagedResource<ServiceType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
