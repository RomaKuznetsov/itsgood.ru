package com.itsgood.ru.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class HttpHeadersConfiguration {

    @Bean
    public HttpHeaders getHeadersBean() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }
}
