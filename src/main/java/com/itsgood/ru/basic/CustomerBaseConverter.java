package com.itsgood.ru.basic;

import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.sql.Timestamp;


public abstract class CustomerBaseConverter<S, T> implements Converter<S, T> {

    public HibernateCustomer doConvert(HibernateCustomer hibernateCustomer, CustomerCreateRequest request) {
        hibernateCustomer.setFirstname(request.getFirstname());
        hibernateCustomer.setLastname(request.getLastname());
        hibernateCustomer.setMail(request.getMail());
        hibernateCustomer.setPhone(request.getPhone());
        hibernateCustomer.setBirthday(request.getBirthday());
        hibernateCustomer.setGender(request.getGender());
        hibernateCustomer.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return hibernateCustomer;
    }
}
