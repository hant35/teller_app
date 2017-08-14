package vn.fpt.util.json;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateConverter {
	
    public static class Serialize extends JsonSerializer<Date> {
        
    	@Override
    	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value == null) {
                jgen.writeNull();
            }
            else {
            	jgen.writeNull();
            }
        }
    }

    public static class Deserialize extends JsonDeserializer<Date> {
    	@Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            String dateAsString = jp.getText();
            try {
                if (StringUtils.isEmpty(dateAsString)) {
                    return null;
                }
                else {
                	return DateUtils.parseDate(dateAsString, new String[] {"dd/MM/yyyy", "dd-MM-yyyy", "yyyy-MM-dd", "dd/MM/yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",  "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz"});
                }
            }
            catch (Exception pe) {
            	try {
            		return new java.util.Date(Long.parseLong(dateAsString));
            	}
            	catch (Exception ex) {
            		throw new RuntimeException(pe);
            	}
            }
        }
    }
}