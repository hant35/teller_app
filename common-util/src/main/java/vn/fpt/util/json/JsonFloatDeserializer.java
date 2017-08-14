package vn.fpt.util.json;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class JsonFloatDeserializer extends JsonDeserializer<Float> {

	@Override
	public Float deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String valueAsString = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(valueAsString)) {
            return null;
        }
        else {
        	if ("\\,".equals(DBPConfiguration.NUMBER_GROUPING_SEPARATOR) 
        			&& "\\.".equals(DBPConfiguration.NUMBER_DECIMAL_SEPARATOR)) {
        		java.math.BigDecimal number = new java.math.BigDecimal(valueAsString.replaceAll("\\,", ""));
            	return number.floatValue();
        	}
        	else if ("\\.".equals(DBPConfiguration.NUMBER_GROUPING_SEPARATOR) 
        			&& "\\,".equals(DBPConfiguration.NUMBER_DECIMAL_SEPARATOR)) {
        		java.math.BigDecimal number = new java.math.BigDecimal(valueAsString.replaceAll("\\.", "").replaceAll("\\,", "\\."));
            	return number.floatValue();
        	}
        	else {
        		return null;
        	}
        }
	}
}
