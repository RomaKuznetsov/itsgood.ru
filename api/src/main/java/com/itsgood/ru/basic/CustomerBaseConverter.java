package com.itsgood.ru.basic;

import com.itsgood.ru.domain.CustomerDTO;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;


public abstract class CustomerBaseConverter<S, T> implements Converter<S, T> {

    public CustomerDTO doConvert(CustomerDTO customerDTO, CustomerCreateRequest request) {
        customerDTO.setFirstname(request.getFirstname());
        customerDTO.setLastname(request.getLastname());
        customerDTO.setMail(request.getMail());
        customerDTO.setPhone(request.getPhone());
        customerDTO.setBirthday(request.getBirthday());
        customerDTO.setGender(request.getGender());
        customerDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return customerDTO;
    }
}
