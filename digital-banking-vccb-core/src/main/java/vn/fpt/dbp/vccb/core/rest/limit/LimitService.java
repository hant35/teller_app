package vn.fpt.dbp.vccb.core.rest.limit;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class LimitService extends ClientService {
	public static Limit read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Limit> responseTypeRef = new ParameterizedTypeReference<Limit>() {
			};
			ResponseEntity<Limit> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Limit> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Limit>> responseTypeRef = new ParameterizedTypeReference<List<Limit>>() {
			};
			ResponseEntity<List<Limit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<Limit> body = (List<Limit>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Limit> cud(String restUrl, Limit model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Limit>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Limit>>() {
			};

			HttpEntity<Limit> httpEntity = new HttpEntity<Limit>(model);
			ResponseEntity<RestResponse<Limit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Limit> search(String restUrl, Limit model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Limit>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Limit>>() {
			};

			HttpEntity<Limit> httpEntity = new HttpEntity<Limit>(model);
			ResponseEntity<PagedResource<Limit>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/*
	public static void main(String[] args) throws Exception {
		try {
			setUser("truongdx932", "M6FZRnV5");
			String restUrl = "http://10.86.202.224/tellerapp/admin/api/limit/search";
			PagedResource<Limit> responseEntity = search(restUrl, new Limit());
			System.out.println(responseEntity.getContent().getClass());
		} catch (HttpClientErrorException hcee) {
			System.err.println("==" + new HttpClientErrorException(hcee.getStatusCode()) + "==");
			throw new Exception(hcee.getStatusText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
