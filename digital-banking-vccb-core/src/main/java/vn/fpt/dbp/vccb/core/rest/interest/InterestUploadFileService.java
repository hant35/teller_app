package vn.fpt.dbp.vccb.core.rest.interest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.interest.InterestUploadFile;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class InterestUploadFileService extends ClientService {
	public static InterestUploadFile read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<InterestUploadFile> responseTypeRef = new ParameterizedTypeReference<InterestUploadFile>() {
			};
			ResponseEntity<InterestUploadFile> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<InterestUploadFile> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<InterestUploadFile>> responseTypeRef = new ParameterizedTypeReference<List<InterestUploadFile>>() {
			};
			ResponseEntity<List<InterestUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<InterestUploadFile> body = (List<InterestUploadFile>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<InterestUploadFile> cud(String restUrl, InterestUploadFile model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<InterestUploadFile>> responseTypeRef = new ParameterizedTypeReference<RestResponse<InterestUploadFile>>() {
			};

			HttpEntity<InterestUploadFile> httpEntity = new HttpEntity<InterestUploadFile>(model);
			ResponseEntity<RestResponse<InterestUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<InterestUploadFile> search(String restUrl, InterestUploadFile model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<InterestUploadFile>> responseTypeRef = new ParameterizedTypeReference<PagedResource<InterestUploadFile>>() {
			};

			HttpEntity<InterestUploadFile> httpEntity = new HttpEntity<InterestUploadFile>(model);
			ResponseEntity<PagedResource<InterestUploadFile>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
