package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.Position;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class PositionService extends ClientService {
	public static Position read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Position> responseTypeRef = new ParameterizedTypeReference<Position>() {
			};
			ResponseEntity<Position> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Position> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Position>> responseTypeRef = new ParameterizedTypeReference<List<Position>>() {
			};
			ResponseEntity<List<Position>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Position> body = (List<Position>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Position> cud(String restUrl, Position model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Position>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Position>>() {
			};

			HttpEntity<Position> httpEntity = new HttpEntity<Position>(model);
			ResponseEntity<RestResponse<Position>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Position> search(String restUrl, Position model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Position>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Position>>() {
			};

			HttpEntity<Position> httpEntity = new HttpEntity<Position>(model);
			ResponseEntity<PagedResource<Position>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
