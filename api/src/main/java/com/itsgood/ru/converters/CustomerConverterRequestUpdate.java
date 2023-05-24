package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.domain.CustomerDTO;
import org.springframework.core.convert.converter.Converter;

public interface CustomerConverterRequestUpdate extends Converter<CustomerRequestUpdate, CustomerDTO> {
}
