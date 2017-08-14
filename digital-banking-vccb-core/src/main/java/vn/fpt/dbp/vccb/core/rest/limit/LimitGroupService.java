package vn.fpt.dbp.vccb.core.rest.limit;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.limit.LimitGroup;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class LimitGroupService extends ClientService {
	public static LimitGroup read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<LimitGroup> responseTypeRef = new ParameterizedTypeReference<LimitGroup>() {
			};
			ResponseEntity<LimitGroup> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<LimitGroup> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<LimitGroup>> responseTypeRef = new ParameterizedTypeReference<List<LimitGroup>>() {
			};
			ResponseEntity<List<LimitGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<LimitGroup> body = (List<LimitGroup>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<LimitGroup> cud(String restUrl, LimitGroup model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<LimitGroup>> responseTypeRef = new ParameterizedTypeReference<RestResponse<LimitGroup>>() {
			};

			HttpEntity<LimitGroup> httpEntity = new HttpEntity<LimitGroup>(model);
			ResponseEntity<RestResponse<LimitGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<LimitGroup> search(String restUrl, LimitGroup model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<LimitGroup>> responseTypeRef = new ParameterizedTypeReference<PagedResource<LimitGroup>>() {
			};

			HttpEntity<LimitGroup> httpEntity = new HttpEntity<LimitGroup>(model);
			ResponseEntity<PagedResource<LimitGroup>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
