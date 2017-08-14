package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.ServiceGroupType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ServiceGroupTypeService extends ClientService {
	public static ServiceGroupType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ServiceGroupType> responseTypeRef = new ParameterizedTypeReference<ServiceGroupType>() {
			};
			ResponseEntity<ServiceGroupType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ServiceGroupType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ServiceGroupType>> responseTypeRef = new ParameterizedTypeReference<List<ServiceGroupType>>() {
			};
			ResponseEntity<List<ServiceGroupType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<ServiceGroupType> body = (List<ServiceGroupType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ServiceGroupType> cud(String restUrl, ServiceGroupType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ServiceGroupType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ServiceGroupType>>() {
			};

			HttpEntity<ServiceGroupType> httpEntity = new HttpEntity<ServiceGroupType>(model);
			ResponseEntity<RestResponse<ServiceGroupType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ServiceGroupType> search(String restUrl, ServiceGroupType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ServiceGroupType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ServiceGroupType>>() {
			};

			HttpEntity<ServiceGroupType> httpEntity = new HttpEntity<ServiceGroupType>(model);
			ResponseEntity<PagedResource<ServiceGroupType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
