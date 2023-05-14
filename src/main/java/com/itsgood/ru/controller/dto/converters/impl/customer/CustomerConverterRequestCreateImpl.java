package com.itsgood.ru.controller.dto.converters.impl.customer;

import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestCreateImpl implements CustomerConverterRequestCreate {

    @Override
    public HibernateCustomer convert(CustomerRequestCreate customerRequestCreate) {
        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        hibernateCustomer.setFirstname(customerRequestCreate.getFirstname());
        hibernateCustomer.setLastname(customerRequestCreate.getLastname());
        hibernateCustomer.setMail(customerRequestCreate.getMail());
        hibernateCustomer.setPhone(customerRequestCreate.getPhone());
        hibernateCustomer.setBirthday(customerRequestCreate.getBirthday());
        hibernateCustomer.setGender(customerRequestCreate.getGender());
        hibernateCustomer.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        authenticationInfo.setUsername(customerRequestCreate.getUsername());
        authenticationInfo.setPassword(customerRequestCreate.getPassword());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;

    }
}