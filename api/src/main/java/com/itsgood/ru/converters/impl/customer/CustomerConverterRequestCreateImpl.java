package com.itsgood.ru.converters.impl.customer;

import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.AuthenticationInfo;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestCreateImpl implements CustomerConverterRequestCreate {

    private final AuthenticationInfo authenticationInfo;
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO convert(CustomerRequestCreate request) {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(request.getFirstname());
        customerDTO.setLastname(request.getLastname());
        customerDTO.setMail(request.getMail());
        customerDTO.setPhone(request.getPhone());
        customerDTO.setBirthday(request.getBirthday());
        customerDTO.setGender(request.getGender());
        customerDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
        authenticationInfo.setPassword(passwordEncoder.encode(password));
        authenticationInfo.setPassword(request.getUsername());
        customerDTO.setAuthenticationInfo(authenticationInfo);
        return customerDTO;

    }
}