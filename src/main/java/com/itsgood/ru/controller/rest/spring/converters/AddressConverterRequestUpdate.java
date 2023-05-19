package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import org.springframework.core.convert.converter.Converter;

public interface AddressConverterRequestUpdate extends Converter<AddressRequestUpdate, HibernateAddress> {
}
