package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import org.springframework.core.convert.converter.Converter;

public interface AddressConverterRequestCreate extends Converter<AddressRequestCreate, HibernateAddress> {
}
