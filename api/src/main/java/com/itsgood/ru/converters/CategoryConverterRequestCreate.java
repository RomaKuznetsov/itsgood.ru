package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateCategory;
import org.springframework.core.convert.converter.Converter;

public interface CategoryConverterRequestCreate extends Converter<CategoryRequestCreate, HibernateCategory> {
}
