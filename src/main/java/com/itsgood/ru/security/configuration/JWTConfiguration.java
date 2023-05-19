package com.itsgood.ru.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@NoArgsConstructor
@Configuration
@Getter
@Setter
@ConfigurationProperties("jwtconfig")
public class JWTConfiguration {

    private String secret;
    //количество милисекунд на которое выдается токен для аутиенфикации
    private Integer expiration;

}
