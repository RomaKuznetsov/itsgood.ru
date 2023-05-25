package com.itsgood.ru.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
