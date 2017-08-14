package vn.fpt.dbp.vccb.core.rest.organization;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.organization.Address;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class AddressService extends ClientService {
	public static Address read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<Address> responseTypeRef = new ParameterizedTypeReference<Address>() {
			};
			ResponseEntity<Address> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<Address> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<Address>> responseTypeRef = new ParameterizedTypeReference<List<Address>>() {
			};
			ResponseEntity<List<Address>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null,
					responseTypeRef);
			List<Address> body = (List<Address>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<Address> cud(String restUrl, Address model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<Address>> responseTypeRef = new ParameterizedTypeReference<RestResponse<Address>>() {
			};

			HttpEntity<Address> httpEntity = new HttpEntity<Address>(model);
			ResponseEntity<RestResponse<Address>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<Address> search(String restUrl, Address model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<Address>> responseTypeRef = new ParameterizedTypeReference<PagedResource<Address>>() {
			};

			HttpEntity<Address> httpEntity = new HttpEntity<Address>(model);
			ResponseEntity<PagedResource<Address>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST,
					httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
