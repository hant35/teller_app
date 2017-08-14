package vn.fpt.util.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class RestClient {
	
	public static <T> PagedResource<T> search(String url, T param) throws Exception {
		ParameterizedTypeReference<PagedResource<T>> responseTypeRef = new ParameterizedTypeReference<PagedResource<T>>() {};
		
		HttpEntity<T> httpEntity = new HttpEntity<T>(param);
		ResponseEntity<PagedResource<T>> responseEntity = new DBPRestTemplateFactory().getRestTemplate().exchange(url, HttpMethod.POST, httpEntity, responseTypeRef);
		return responseEntity.getBody();
	}
	
	public static <T> T doGet(String url, Class<T> type) throws Exception {
		DBPRestTemplateFactory factory = new DBPRestTemplateFactory();
//		ParameterizedTypeReference<T> responseTypeRef = new ParameterizedTypeReference<T>() {};
//	    ResponseEntity<T> responseEntity = factory.getRestTemplate().exchange(url, HttpMethod.GET, (HttpEntity) null, responseTypeRef);
//	    return responseEntity.getBody();
		
		
//		if (type.getName().equals("java.lang.String")) {
//			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     
//
//			body.add("field", "value");
//
//			// Note the body object as first parameter!
//			HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);			
//			factory.getRestTemplate().excha
//		}
		return factory.getRestTemplate().getForObject(url, type);
	}
	
	public static <T> T doPost(String url, Object param, Class<T> type) throws Exception {
		DBPRestTemplateFactory factory = new DBPRestTemplateFactory();
		return factory.getRestTemplate().postForObject(url, param, type);
	}

	public static void doPost(String url, Object param) throws Exception {
		DBPRestTemplateFactory factory = new DBPRestTemplateFactory();
		factory.getRestTemplate().postForObject(url, param, Object.class);
	}
	
	public static void main(String[] args) {
	}
}
