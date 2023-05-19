package com.itsgood.ru.converters.impl.category;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.converters.CategoryConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateCategory;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterRequestCreateImpl implements CategoryConverterRequestCreate {
    @Override
    public HibernateCategory convert(CategoryRequestCreate request) {
        HibernateCategory hibernateCategory = new HibernateCategory();
        hibernateCategory.setTitle(request.getTitle());
        hibernateCategory.setDescription(request.getDescription());
        return hibernateCategory;
    }
}
