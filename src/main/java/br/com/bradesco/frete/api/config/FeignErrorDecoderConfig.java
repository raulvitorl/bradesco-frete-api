package br.com.bradesco.frete.api.config;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bradesco.frete.api.exception.handler.FeignErrorDecoder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignErrorDecoderConfig {
    private ObjectFactory<HttpMessageConverters> getObjectFactory(ObjectMapper objectMapper) {
        return () -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper));
    }

    @Bean
    Encoder feignFormEncoder(ObjectMapper objectMapper) {
        return new SpringFormEncoder(new SpringEncoder(getObjectFactory(objectMapper)));
    }

    @Bean
    Decoder feignFormDecoder(ObjectMapper objectMapper) {
        return new SpringDecoder(getObjectFactory(objectMapper));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}