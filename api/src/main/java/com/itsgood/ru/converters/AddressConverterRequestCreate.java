package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.address.AddressRequestCreate;
import com.itsgood.ru.domain.AddressDTO;
import org.springframework.core.convert.converter.Converter;

public interface AddressConverterRequestCreate extends Converter<AddressRequestCreate, AddressDTO> {
}
