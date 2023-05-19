package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateContract_item;
import org.springframework.core.convert.converter.Converter;

public interface Contract_itemConverterRequestUpdate extends Converter<Contract_itemRequestUpdate, HibernateContract_item> {
}
