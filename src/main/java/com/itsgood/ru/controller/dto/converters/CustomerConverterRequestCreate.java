package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import org.springframework.core.convert.converter.Converter;

public interface CustomerConverterRequestCreate extends Converter<CustomerRequestCreate, HibernateCustomer> {
}
