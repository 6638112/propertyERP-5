package com.cnfantasia.server.api.pub;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.cnfantasia.server.common.json.JsonResponse;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

/**  
* 类说明   
*  
* @author yewj  
* @time   2016年7月29日 下午8:50:47
*/
public class MappingJackson2JsonpHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	@SuppressWarnings("deprecation")
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
        JsonGenerator jsonGenerator = this.getObjectMapper().getFactory().createJsonGenerator(outputMessage.getBody(), encoding);        

        try {
            String jsonPadding = null;            

            // If the callback doesn't provide, use the default callback
            if (object instanceof JsonResponse) {
                jsonPadding = ((JsonResponse)object).getJsonCallback();
            }
            if(jsonPadding == null) {
            	super.writeInternal(object, outputMessage);
            } else {
            	jsonGenerator.writeRaw(jsonPadding);
                jsonGenerator.writeRaw('(');
                this.getObjectMapper().writeValue(jsonGenerator, object);
                jsonGenerator.writeRaw(");");
                jsonGenerator.flush();
            }
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
	}

}
