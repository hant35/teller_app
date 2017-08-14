package vn.fpt.util.rest;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import vn.fpt.util.json.DateConverter;
import vn.fpt.util.json.JsonDoubleDeserializer;
import vn.fpt.util.json.JsonFloatDeserializer;
import vn.fpt.util.json.JsonIntDeserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

//import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class DBPRestTemplateFactory {

	private ObjectMapper objectMapper;
	
	private static ClientHttpRequestFactory requestFactory;
	static {
		HttpHost targetHost = new HttpHost("localhost", 80, "http");
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);
		
		CacheConfig cacheConfig =  CacheConfig.custom().setMaxCacheEntries(1000).setMaxObjectSize(8192000).setSharedCache(false).build();
		RequestConfig requestConfig = RequestConfig.custom()
		        .setConnectTimeout(30000)
		        .setSocketTimeout(30000)
		        .build();
		CloseableHttpClient cachingClient = CachingHttpClients.custom()
		        .setCacheConfig(cacheConfig)
		        .setDefaultRequestConfig(requestConfig)
		        .build();
		//HttpClient cachingClient = new CachingHttpClient(new DefaultHttpClient(), cacheConfig);

		requestFactory = new HttpComponentsClientHttpRequestFactory(cachingClient);
		
	}
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(requestFactory) {

			@Override
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
				// TODO Auto-generated method stub
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType, uriVariables);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType, uriVariables);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType, uriVariables);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType, uriVariables);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(url, method, requestEntity, responseType);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(requestEntity, responseType);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

			@Override
			public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
				ResponseEntity<T> resp = null;
				try {
					resp = super.exchange(requestEntity, responseType);
				} catch (RestClientException e) {
					if (e instanceof HttpClientErrorException) {
						throw new HttpClientErrorException(((HttpClientErrorException) e).getStatusCode());
					} else {
						throw e;
					}
				}
				return resp;
			}

		};
		// restTemplate.getMessageConverters().clear(); //important
		// List<HttpMessageConverter> converters = new
		// ArrayList<HttpMessageConverter>();
		// if (restTemplate.getMessageConverters() != null) {
		// for (Object obj : restTemplate.getMessageConverters()) {
		// //System.out.println("xxxxxxxxxxxxCONEVERTERxxxx::: " + obj);
		// }
		// }
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));// .add(stringHttpMessageConverter);//new
																												// StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.getMessageConverters().add(1, new MappingJackson2HttpMessageConverter(objectMapper()));

		/*
		 * List<HttpMessageConverter<?>> messageConverters = new
		 * ArrayList<HttpMessageConverter<?>>(); messageConverters.add(new
		 * MappingJackson2HttpMessageConverter(objectMapper()));
		 * messageConverters.add(new StringHttpMessageConverter());
		 * restTemplate.setMessageConverters(messageConverters);
		 */
		return restTemplate;
	}

	public ObjectMapper objectMapper() {
		if (objectMapper == null) {
			// //NoDeduplicateIdDeserializationContext deserializationContext =
			// new
			// NoDeduplicateIdDeserializationContext(BeanDeserializerFactory.instance);
			// //objectMapper = new ObjectMapper(null, null,
			// deserializationContext);
			objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
			objectMapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);

			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			// objectMapper.setSerializationInclusion(Include.NON_EMPTY); //see:
			// http://stackoverflow.com/questions/38828536/how-to-ignore-null-or-empty-properties-in-json-globally-using-spring-configu

			// //objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			// //objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
			// false);
			// objectMapper.registerModule((new
			// Hibernate4Module()).enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING));
			/*
			 * objectMapper.findAndRegisterModules();
			 * objectMapper.disable(SerializationFeature
			 * .WRITE_DATES_AS_TIMESTAMPS);
			 * objectMapper.disable(DeserializationFeature
			 * .FAIL_ON_UNKNOWN_PROPERTIES);
			 * 
			 * //objectMapper.disable(DeserializationFeature.
			 * FAIL_ON_READING_DUP_TREE_KEY);
			 * //objectMapper.disable(DeserializationFeature
			 * .FAIL_ON_UNRESOLVED_OBJECT_IDS);
			 * 
			 * objectMapper.registerModule(customDatetimeModule());
			 * objectMapper.registerModule(customDoubleModule());
			 * objectMapper.registerModule(customFloatModule());
			 * objectMapper.registerModule(customIntModule());
			 */
		}
		return objectMapper;
	}

	public Module customDatetimeModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Date.class, new DateConverter.Deserialize());
		return module;
	}

	public Module customDoubleModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Double.class, new JsonDoubleDeserializer());
		return module;
	}

	public Module customFloatModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Float.class, new JsonFloatDeserializer());
		return module;
	}

	@Bean
	public Module customIntModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Integer.class, new JsonIntDeserializer());
		return module;
	}

}
