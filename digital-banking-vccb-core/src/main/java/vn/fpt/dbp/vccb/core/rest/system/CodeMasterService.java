package vn.fpt.dbp.vccb.core.rest.system;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.system.CodeMaster;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CodeMasterService extends ClientService {
	public static CodeMaster read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CodeMaster> responseTypeRef = new ParameterizedTypeReference<CodeMaster>() {
			};
			ResponseEntity<CodeMaster> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CodeMaster> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CodeMaster>> responseTypeRef = new ParameterizedTypeReference<List<CodeMaster>>() {
			};
			ResponseEntity<List<CodeMaster>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<CodeMaster> body = (List<CodeMaster>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CodeMaster> cud(String restUrl, CodeMaster model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CodeMaster>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CodeMaster>>() {
			};

			HttpEntity<CodeMaster> httpEntity = new HttpEntity<CodeMaster>(model);
			ResponseEntity<RestResponse<CodeMaster>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CodeMaster> search(String restUrl, CodeMaster model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CodeMaster>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CodeMaster>>() {
			};

			HttpEntity<CodeMaster> httpEntity = new HttpEntity<CodeMaster>(model);
			ResponseEntity<PagedResource<CodeMaster>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

}
