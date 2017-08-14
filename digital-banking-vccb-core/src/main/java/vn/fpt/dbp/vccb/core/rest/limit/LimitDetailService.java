package vn.fpt.dbp.vccb.core.rest.limit;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.limit.LimitDetail;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class LimitDetailService extends ClientService {
	public static LimitDetail read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<LimitDetail> responseTypeRef = new ParameterizedTypeReference<LimitDetail>() {
			};
			ResponseEntity<LimitDetail> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<LimitDetail> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<LimitDetail>> responseTypeRef = new ParameterizedTypeReference<List<LimitDetail>>() {
			};
			ResponseEntity<List<LimitDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<LimitDetail> body = (List<LimitDetail>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<LimitDetail> cud(String restUrl, LimitDetail model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<LimitDetail>> responseTypeRef = new ParameterizedTypeReference<RestResponse<LimitDetail>>() {
			};

			HttpEntity<LimitDetail> httpEntity = new HttpEntity<LimitDetail>(model);
			ResponseEntity<RestResponse<LimitDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<LimitDetail> search(String restUrl, LimitDetail model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<LimitDetail>> responseTypeRef = new ParameterizedTypeReference<PagedResource<LimitDetail>>() {
			};

			HttpEntity<LimitDetail> httpEntity = new HttpEntity<LimitDetail>(model);
			ResponseEntity<PagedResource<LimitDetail>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
