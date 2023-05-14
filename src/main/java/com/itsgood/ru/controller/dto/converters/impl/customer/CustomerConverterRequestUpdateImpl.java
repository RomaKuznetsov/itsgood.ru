package com.itsgood.ru.controller.dto.converters.impl.customer;

import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.service.CustomerDataService;
import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestUpdateImpl implements CustomerConverterRequestUpdate {

    @Override
    public HibernateCustomer convert(CustomerRequestUpdate request) {
        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        hibernateCustomer.setId(request.getId());
        hibernateCustomer.setFirstname(request.getFirstname());
        hibernateCustomer.setLastname(request.getLastname());
        hibernateCustomer.setMail(request.getMail());
        hibernateCustomer.setPhone(request.getPhone());
        hibernateCustomer.setBirthday(request.getBirthday());
        hibernateCustomer.setGender(request.getGender());
        hibernateCustomer.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        authenticationInfo.setPassword(request.getUsername());
        authenticationInfo.setPassword(request.getPassword());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;
    }
}
