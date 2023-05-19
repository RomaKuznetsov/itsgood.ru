package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import org.springframework.core.convert.converter.Converter;

public interface Contract_itemConverterRequestCreate extends Converter<Contract_itemRequestCreate, HibernateContract_item> {
}
