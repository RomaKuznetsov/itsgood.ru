package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestCreate;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import org.springframework.core.convert.converter.Converter;

public interface Contract_itemConverterRequestCreate extends Converter<Contract_itemRequestCreate, Contract_itemDTO> {
}
