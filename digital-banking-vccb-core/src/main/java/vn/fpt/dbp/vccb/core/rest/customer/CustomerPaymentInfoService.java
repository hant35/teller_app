package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerPaymentInfo;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class CustomerPaymentInfoService extends ClientService {
	public static CustomerPaymentInfo read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<CustomerPaymentInfo> responseTypeRef = new ParameterizedTypeReference<CustomerPaymentInfo>() {
			};
			ResponseEntity<CustomerPaymentInfo> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET,
					null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<CustomerPaymentInfo> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<CustomerPaymentInfo>> responseTypeRef = new ParameterizedTypeReference<List<CustomerPaymentInfo>>() {
			};
			ResponseEntity<List<CustomerPaymentInfo>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<CustomerPaymentInfo> body = (List<CustomerPaymentInfo>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<CustomerPaymentInfo> cud(String restUrl, CustomerPaymentInfo model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<CustomerPaymentInfo>> responseTypeRef = new ParameterizedTypeReference<RestResponse<CustomerPaymentInfo>>() {
			};

			HttpEntity<CustomerPaymentInfo> httpEntity = new HttpEntity<CustomerPaymentInfo>(model);
			ResponseEntity<RestResponse<CustomerPaymentInfo>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<CustomerPaymentInfo> search(String restUrl, CustomerPaymentInfo model)
			throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<CustomerPaymentInfo>> responseTypeRef = new ParameterizedTypeReference<PagedResource<CustomerPaymentInfo>>() {
			};

			HttpEntity<CustomerPaymentInfo> httpEntity = new HttpEntity<CustomerPaymentInfo>(model);
			ResponseEntity<PagedResource<CustomerPaymentInfo>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
