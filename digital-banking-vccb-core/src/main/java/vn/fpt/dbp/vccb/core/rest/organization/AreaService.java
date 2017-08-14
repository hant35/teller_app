package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AreaService extends ClientService {
	public static Area read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Area> responseTypeRef = new ParameterizedTypeReference<Area>() {
			};
			ResponseEntity<Area> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Area> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Area>> responseTypeRef = new ParameterizedTypeReference<List<Area>>() {
			};
			ResponseEntity<List<Area>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Area> body = (List<Area>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Area> cud(String restUrl, Area model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Area>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Area>>() {
			};

			HttpEntity<Area> httpEntity = new HttpEntity<Area>(model);
			ResponseEntity<RestResponse<Area>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Area> search(String restUrl, Area model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Area>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Area>>() {
			};

			HttpEntity<Area> httpEntity = new HttpEntity<Area>(model);
			ResponseEntity<PagedResource<Area>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Area> searchByProduct(String restUrl, Product model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Area>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Area>>() {
			};

			HttpEntity<Product> httpEntity = new HttpEntity<Product>(model);
			ResponseEntity<PagedResource<Area>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
