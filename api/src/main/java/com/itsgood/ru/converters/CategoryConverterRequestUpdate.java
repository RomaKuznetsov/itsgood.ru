package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateCategory;
import org.springframework.core.convert.converter.Converter;

public interface CategoryConverterRequestUpdate extends Converter<CategoryRequestUpdate, HibernateCategory> {
}
