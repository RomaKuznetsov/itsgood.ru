package com.itsgood.ru.hibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.net.httpserver.HttpPrincipal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Component
public class AuthenticationInfo {

    private String username;
    @JsonIgnore
    private String password;

}
