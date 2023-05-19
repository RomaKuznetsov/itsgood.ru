package com.itsgood.ru.controller.dto.converters.impl.category;

import com.itsgood.ru.controller.dto.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterRequestUpdateImpl implements CategoryConverterRequestUpdate {

    @Override
    public HibernateCategory convert(CategoryRequestUpdate request) {
        HibernateCategory hibernateCategory = new HibernateCategory();
        hibernateCategory.setId(request.getId());
        hibernateCategory.setTitle(request.getTitle());
        hibernateCategory.setDescription(request.getDescription());
        return hibernateCategory;
    }
}
