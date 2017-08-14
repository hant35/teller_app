package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerImage;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerImageService extends ClientService {
	public static CustomerImage read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerImage> responseTypeRef = new ParameterizedTypeReference<CustomerImage>() {
			};
			ResponseEntity<CustomerImage> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerImage> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerImage>> responseTypeRef = new ParameterizedTypeReference<List<CustomerImage>>() {
			};
			ResponseEntity<List<CustomerImage>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			List<CustomerImage> body = (List<CustomerImage>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerImage> cud(String restUrl, CustomerImage model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerImage>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerImage>>() {
			};

			HttpEntity<CustomerImage> httpEntity = new HttpEntity<CustomerImage>(model);
			ResponseEntity<RestResponse<CustomerImage>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerImage> search(String restUrl, CustomerImage model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerImage>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerImage>>() {
			};

			HttpEntity<CustomerImage> httpEntity = new HttpEntity<CustomerImage>(model);
			ResponseEntity<PagedResource<CustomerImage>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
