package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import org.springframework.core.convert.converter.Converter;

public interface ItemConverterRequestCreate extends Converter<ItemRequestCreate, ItemDTO> {
}
