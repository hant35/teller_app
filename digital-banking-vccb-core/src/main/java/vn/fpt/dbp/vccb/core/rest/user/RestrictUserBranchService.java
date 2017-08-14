package vn.fpt.dbp.vccb.core.rest.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserBranch;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class RestrictUserBranchService extends ClientService {
	public static RestrictUserBranch read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<RestrictUserBranch> responseTypeRef = new ParameterizedTypeReference<RestrictUserBranch>() {
			};
			ResponseEntity<RestrictUserBranch> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<RestrictUserBranch> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<RestrictUserBranch>> responseTypeRef = new ParameterizedTypeReference<List<RestrictUserBranch>>() {
			};
			ResponseEntity<List<RestrictUserBranch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<RestrictUserBranch> body = (List<RestrictUserBranch>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<RestrictUserBranch> cud(String restUrl, RestrictUserBranch model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<RestrictUserBranch>> responseTypeRef = new ParameterizedTypeReference<RestResponse<RestrictUserBranch>>() {
			};

			HttpEntity<RestrictUserBranch> httpEntity = new HttpEntity<RestrictUserBranch>(model);
			ResponseEntity<RestResponse<RestrictUserBranch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<RestrictUserBranch> search(String restUrl, RestrictUserBranch model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<RestrictUserBranch>> responseTypeRef = new ParameterizedTypeReference<PagedResource<RestrictUserBranch>>() {
			};

			HttpEntity<RestrictUserBranch> httpEntity = new HttpEntity<RestrictUserBranch>(model);
			ResponseEntity<PagedResource<RestrictUserBranch>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
