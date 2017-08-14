package vn.fpt.dbp.vccb.core.rest.customer;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import vn.fpt.dbp.vccb.core.domain.customer.FinancialInstitutionsType;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@Deprecated
public class FinancialInstitutionsTypeService extends ClientService {
	public static FinancialInstitutionsType read(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<FinancialInstitutionsType> responseTypeRef = new ParameterizedTypeReference<FinancialInstitutionsType>() {
			};
			ResponseEntity<FinancialInstitutionsType> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static List<FinancialInstitutionsType> findList(String restUrl) throws Exception {
		try {
			ParameterizedTypeReference<List<FinancialInstitutionsType>> responseTypeRef = new ParameterizedTypeReference<List<FinancialInstitutionsType>>() {
			};
			ResponseEntity<List<FinancialInstitutionsType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.GET, null, responseTypeRef);
			List<FinancialInstitutionsType> body = (List<FinancialInstitutionsType>) responseEntity.getBody();
			return body;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static RestResponse<FinancialInstitutionsType> cud(String restUrl, FinancialInstitutionsType model)
			throws Exception {
		try {
			ParameterizedTypeReference<RestResponse<FinancialInstitutionsType>> responseTypeRef = new ParameterizedTypeReference<RestResponse<FinancialInstitutionsType>>() {
			};

			HttpEntity<FinancialInstitutionsType> httpEntity = new HttpEntity<FinancialInstitutionsType>(model);
			ResponseEntity<RestResponse<FinancialInstitutionsType>> responseEntity = getRestTemplate().exchange(restUrl,
					HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public static PagedResource<FinancialInstitutionsType> search(String restUrl, FinancialInstitutionsType model)
			throws Exception {
		try {
			ParameterizedTypeReference<PagedResource<FinancialInstitutionsType>> responseTypeRef = new ParameterizedTypeReference<PagedResource<FinancialInstitutionsType>>() {
			};

			HttpEntity<FinancialInstitutionsType> httpEntity = new HttpEntity<FinancialInstitutionsType>(model);
			ResponseEntity<PagedResource<FinancialInstitutionsType>> responseEntity = getRestTemplate()
					.exchange(restUrl, HttpMethod.POST, httpEntity, responseTypeRef);
			return responseEntity.getBody();
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}
}
