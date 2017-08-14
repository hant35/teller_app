package vn.fpt.dbp.vccb.core.rest.casa;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.casa.ServiceGroup;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ServiceGroupService extends ClientService {
	public static ServiceGroup read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ServiceGroup> responseTypeRef = new ParameterizedTypeReference<ServiceGroup>() {
			};
			ResponseEntity<ServiceGroup> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ServiceGroup> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ServiceGroup>> responseTypeRef = new ParameterizedTypeReference<List<ServiceGroup>>() {
			};
			ResponseEntity<List<ServiceGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<ServiceGroup> body = (List<ServiceGroup>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ServiceGroup> cud(String restUrl, ServiceGroup model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ServiceGroup>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ServiceGroup>>() {
			};

			HttpEntity<ServiceGroup> httpEntity = new HttpEntity<ServiceGroup>(model);
			ResponseEntity<RestResponse<ServiceGroup>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ServiceGroup> search(String restUrl, ServiceGroup model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ServiceGroup>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ServiceGroup>>() {
			};

			HttpEntity<ServiceGroup> httpEntity = new HttpEntity<ServiceGroup>(model);
			ResponseEntity<PagedResource<ServiceGroup>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
