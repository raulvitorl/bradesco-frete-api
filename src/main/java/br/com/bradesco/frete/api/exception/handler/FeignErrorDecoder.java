package br.com.bradesco.frete.api.exception.handler;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import br.com.bradesco.frete.api.exception.FeignCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
	
    private static final String PROCESSO_JA_ABERTO = "A process opened to this assignor already exists.";

	@Override
    public Exception decode(String methodKey, Response response) {

    	if(response.status()==400){
    		String rawResponse =getRawExceptionText(response); 
    		if(rawResponse.contains(PROCESSO_JA_ABERTO)){
    			String processIdText = rawResponse.replaceAll("[^0-9]", "");;
    			return new FeignCustomException(response.status(),processIdText);
    		}else{
    			 return new FeignCustomException(response.status(),rawResponse);
    		}
    	}
    	
        switch (response.status()) {
            default: {
                return new FeignCustomException(response.status(), buildMessage(response));
            }
        }
    }

    private String buildMessage(Response response) {
        try {
            final var body = response.body();
            StringWriter writer = new StringWriter();
            IOUtils.copy(body.asInputStream(), writer, "UTF-8");
            final var details = writer.toString();
            log.error("Resposta da integracao: "+details);
            JSONParser parser = new JSONParser(details);
            final var map = parser.object();
            return getFromMap(map, "path") + " - " + getFromMap(map, "error") + " - " + getFromMap(map, "message");
            
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private String getRawExceptionText(Response response) {
        try {
            final var body = response.body();
            StringWriter writer = new StringWriter();
            IOUtils.copy(body.asInputStream(), writer, "UTF-8");
            final var details = writer.toString();
            return details;           
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFromMap(@SuppressWarnings("rawtypes") LinkedHashMap map, String key) {
        if (!isNull(map.get(key))) {
            return (String) map.get(key);
        }
        return "";
    }

}
