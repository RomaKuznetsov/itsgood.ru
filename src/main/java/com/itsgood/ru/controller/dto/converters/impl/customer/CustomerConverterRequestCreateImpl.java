package com.itsgood.ru.controller.dto.converters.impl.customer;

import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import com.itsgood.ru.security.configuration.WebSecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestCreateImpl implements CustomerConverterRequestCreate {

    private final AuthenticationInfo authenticationInfo;
    private final JWTConfiguration jwtConfiguration;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateCustomer convert(CustomerRequestCreate request) {

        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        hibernateCustomer.setFirstname(request.getFirstname());
        hibernateCustomer.setLastname(request.getLastname());
        hibernateCustomer.setMail(request.getMail());
        hibernateCustomer.setPhone(request.getPhone());
        hibernateCustomer.setBirthday(request.getBirthday());
        hibernateCustomer.setGender(request.getGender());
        hibernateCustomer.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        authenticationInfo.setUsername(request.getUsername());
//        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
//        String encode = new BCryptPasswordEncoder(6).encode(password);
        authenticationInfo.setPassword(request.getPassword());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;

    }
}