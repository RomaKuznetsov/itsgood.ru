package com.itsgood.ru.converters.impl.customer;

import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.AuthenticationInfo;
import com.itsgood.ru.domain.hibernate.HibernateCustomer;
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
    public HibernateCustomer convert(CustomerRequestCreate request) {

        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        hibernateCustomer.setFirstname(request.getFirstname());
        hibernateCustomer.setLastname(request.getLastname());
        hibernateCustomer.setMail(request.getMail());
        hibernateCustomer.setPhone(request.getPhone());
        hibernateCustomer.setBirthday(request.getBirthday());
        hibernateCustomer.setGender(request.getGender());
        hibernateCustomer.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
        authenticationInfo.setPassword(passwordEncoder.encode(password));
        authenticationInfo.setPassword(request.getUsername());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;

    }
}