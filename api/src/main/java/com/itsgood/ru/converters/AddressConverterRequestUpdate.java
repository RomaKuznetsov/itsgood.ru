package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.domain.hibernate.AddressDTO;
import org.springframework.core.convert.converter.Converter;

public interface AddressConverterRequestUpdate extends Converter<AddressRequestUpdate, AddressDTO> {
}
