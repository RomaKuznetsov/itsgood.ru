package com.itsgood.ru.controller.dto.converters.impl.category;

import com.itsgood.ru.controller.dto.converters.CategoryConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
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
