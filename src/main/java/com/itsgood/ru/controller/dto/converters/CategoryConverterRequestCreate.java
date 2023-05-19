package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
import org.springframework.core.convert.converter.Converter;

public interface CategoryConverterRequestCreate extends Converter<CategoryRequestCreate, HibernateCategory> {
}
