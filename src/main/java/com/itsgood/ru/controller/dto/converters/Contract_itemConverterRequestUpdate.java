package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import org.springframework.core.convert.converter.Converter;

public interface Contract_itemConverterRequestUpdate extends Converter<Contract_itemRequestUpdate, HibernateContract_item> {
}
