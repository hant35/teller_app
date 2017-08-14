package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerTypeService extends ClientService{
	public static CustomerType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerType> responseTypeRef = new ParameterizedTypeReference<CustomerType>() {
			};
			ResponseEntity<CustomerType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerType>> responseTypeRef = new ParameterizedTypeReference<List<CustomerType>>() {
			};
			ResponseEntity<List<CustomerType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<CustomerType> body = (List<CustomerType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerType> cud(String restUrl, CustomerType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerType>>() {
			};

			HttpEntity<CustomerType> httpEntity = new HttpEntity<CustomerType>(model);
			ResponseEntity<RestResponse<CustomerType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerType> search(String restUrl, CustomerType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerType>>() {
			};

			HttpEntity<CustomerType> httpEntity = new HttpEntity<CustomerType>(model);
			ResponseEntity<PagedResource<CustomerType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
