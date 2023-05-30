package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import org.springframework.core.convert.converter.Converter;

public interface ItemConverterRequestUpdate extends Converter<ItemRequestUpdate, ItemDTO> {
}
