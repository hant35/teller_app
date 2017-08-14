package vn.fpt.dbp.vccb.service.reference.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
//@ConditionalOnWebApplication
//@ConditionalOnClass(WebMvcConfigurerAdapter.class)
//@ConditionalOnMissingBean({ OpenEntityManagerInViewInterceptor.class,
//		OpenEntityManagerInViewFilter.class })
//@ConditionalOnProperty(prefix = "spring.jpa", name = "open-in-view", havingValue = "true", matchIfMissing = true)
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}
	
	/* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        //MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        
        //NoDeduplicateIdDeserializationContext deserializationContext = new NoDeduplicateIdDeserializationContext(BeanDeserializerFactory.instance);

        //ObjectMapper mapper = new ObjectMapper();
        //ObjectMapper mapper = new ObjectMapper(null, null, deserializationContext);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
        //mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule((new Hibernate4Module()).enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING));
        //mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true); //for pagable, see: http://stackoverflow.com/questions/31913410/spring-data-pagination-returns-no-results-with-jsonview
        
        //messageConverter.setObjectMapper(mapper);
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(0, jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
}
