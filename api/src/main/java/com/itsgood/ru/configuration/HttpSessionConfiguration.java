package com.itsgood.ru.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

@Configuration
@RequiredArgsConstructor
public class HttpSessionConfiguration extends SessionRegistryImpl {

    public HttpSessionConfiguration(ConcurrentMap<Object, Set<String>> principals,
                                    Map<String, SessionInformation> sessionIds) {
        super(principals, sessionIds);
    }
    @Bean
    public SessionRegistryImpl SessionRegistryBean() {
       return new SessionRegistryImpl();
    }

}
