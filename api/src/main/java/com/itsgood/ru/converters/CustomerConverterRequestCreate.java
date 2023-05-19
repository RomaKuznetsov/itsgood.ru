package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import org.springframework.core.convert.converter.Converter;

public interface CustomerConverterRequestCreate extends Converter<CustomerRequestCreate, HibernateCustomer> {
}
