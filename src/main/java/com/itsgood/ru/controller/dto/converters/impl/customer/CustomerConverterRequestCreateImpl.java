package com.itsgood.ru.controller.dto.converters.impl.customer;

import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestCreateImpl implements CustomerConverterRequestCreate {

    private final AuthenticationInfo authenticationInfo;
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
        authenticationInfo.setPassword(request.getPassword());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;

    }
}