package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import org.springframework.core.convert.converter.Converter;

public interface ItemConverterRequestCreate extends Converter<ItemRequestCreate, HibernateItem> {
}
