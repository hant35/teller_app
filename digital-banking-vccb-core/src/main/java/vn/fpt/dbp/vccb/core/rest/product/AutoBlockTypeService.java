package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.AutoBlockType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AutoBlockTypeService extends ClientService {
	public static AutoBlockType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<AutoBlockType> responseTypeRef = new ParameterizedTypeReference<AutoBlockType>() {
			};
			ResponseEntity<AutoBlockType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<AutoBlockType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<AutoBlockType>> responseTypeRef = new ParameterizedTypeReference<List<AutoBlockType>>() {
			};
			ResponseEntity<List<AutoBlockType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<AutoBlockType> body = (List<AutoBlockType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<AutoBlockType> cud(String restUrl, AutoBlockType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<AutoBlockType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<AutoBlockType>>() {
			};

			HttpEntity<AutoBlockType> httpEntity = new HttpEntity<AutoBlockType>(model);
			ResponseEntity<RestResponse<AutoBlockType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<AutoBlockType> search(String restUrl, AutoBlockType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<AutoBlockType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<AutoBlockType>>() {
			};

			HttpEntity<AutoBlockType> httpEntity = new HttpEntity<AutoBlockType>(model);
			ResponseEntity<PagedResource<AutoBlockType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
