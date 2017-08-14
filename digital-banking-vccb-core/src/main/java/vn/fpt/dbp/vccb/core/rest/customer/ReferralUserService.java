package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.ReferralUser;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class ReferralUserService extends ClientService {
	public static ReferralUser read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<ReferralUser> responseTypeRef = new ParameterizedTypeReference<ReferralUser>() {
			};
			ResponseEntity<ReferralUser> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<ReferralUser> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<ReferralUser>> responseTypeRef = new ParameterizedTypeReference<List<ReferralUser>>() {
			};
			ResponseEntity<List<ReferralUser>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<ReferralUser> body = (List<ReferralUser>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<ReferralUser> cud(String restUrl, ReferralUser model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<ReferralUser>> responseTypeRef = new ParameterizedTypeReference<RestResponse<ReferralUser>>() {
			};

			HttpEntity<ReferralUser> httpEntity = new HttpEntity<ReferralUser>(model);
			ResponseEntity<RestResponse<ReferralUser>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<ReferralUser> search(String restUrl, ReferralUser model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<ReferralUser>> responseTypeRef = new ParameterizedTypeReference<PagedResource<ReferralUser>>() {
			};

			HttpEntity<ReferralUser> httpEntity = new HttpEntity<ReferralUser>(model);
			ResponseEntity<PagedResource<ReferralUser>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
