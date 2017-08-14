package vn.fpt.dbp.vccb.core.rest.product;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.product.DepositType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

public class DepositTypeService extends ClientService {
	public static DepositType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<DepositType> responseTypeRef = new ParameterizedTypeReference<DepositType>() {
			};
			ResponseEntity<DepositType> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<DepositType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<DepositType>> responseTypeRef = new ParameterizedTypeReference<List<DepositType>>() {
			};
			ResponseEntity<List<DepositType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.GET, null, responseTypeRef);
			List<DepositType> body = (List<DepositType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<DepositType> cud(String restUrl, DepositType model) throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<DepositType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<DepositType>>() {
			};

			HttpEntity<DepositType> httpEntity = new HttpEntity<DepositType>(model);
			ResponseEntity<RestResponse<DepositType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<DepositType> search(String restUrl, DepositType model) throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<DepositType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<DepositType>>() {
			};

			HttpEntity<DepositType> httpEntity = new HttpEntity<DepositType>(model);
			ResponseEntity<PagedResource<DepositType>> responseEntity = getRestTemplate().exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
