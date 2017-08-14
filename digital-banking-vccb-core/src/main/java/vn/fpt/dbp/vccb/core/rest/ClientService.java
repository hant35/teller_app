package vn.fpt.dbp.vccb.core.rest;

import org.apache.commons.codec.binary.Base64;
//import java.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.util.rest.DBPRestTemplateFactory;

public class ClientService {
	protected static ThreadLocal<UserContext> userContext = new ThreadLocal<ClientService.UserContext>();

	
	public static void setUser(String username, String password) {
		userContext.set(new UserContext(username, password));
	}

	public static UserContext getUserContext() {
		return userContext.get();
	}

	public static class UserContext {
		@Getter
		@Setter
		private String username;
		@Getter
		@Setter
		private String password;

		public UserContext(String username, String password) {
			this.username = username;
			this.password = password;
		}
	}

	public static HttpHeaders createHeaders(final String username, final String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
				//byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	public static RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new DBPRestTemplateFactory().getRestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(getUserContext().getUsername(), getUserContext().getPassword()));
		return restTemplate;
	}
	
	public static void handleException(Exception e) throws Exception {
		if (e instanceof HttpClientErrorException) {
			throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
		} else {
			throw e;
		}
	}
}
