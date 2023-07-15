package com.example.countryborders.configuration;

import feign.Retryer;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@Configuration
public class FeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        var converter = new GsonHttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(APPLICATION_JSON, TEXT_PLAIN));

        return new SpringDecoder(() -> new HttpMessageConverters(converter));
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
