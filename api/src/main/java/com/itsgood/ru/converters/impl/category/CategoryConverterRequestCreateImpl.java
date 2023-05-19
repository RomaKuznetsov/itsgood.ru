package com.itsgood.ru.converters.impl.category;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.converters.CategoryConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterRequestCreateImpl implements CategoryConverterRequestCreate {
    @Override
    public CategoryDTO convert(CategoryRequestCreate request) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setTitle(request.getTitle());
        categoryDTO.setDescription(request.getDescription());
        return categoryDTO;
    }
}
