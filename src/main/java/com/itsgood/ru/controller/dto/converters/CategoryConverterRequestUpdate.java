package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
import org.springframework.core.convert.converter.Converter;

public interface CategoryConverterRequestUpdate extends Converter<CategoryRequestUpdate, HibernateCategory> {
}
