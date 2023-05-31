package com.itsgood.ru.converters.impl.category;

import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CategoryDTO;
import com.itsgood.ru.repository.spring.CategoryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryConverterRequestUpdateImpl implements CategoryConverterRequestUpdate {

    private final CategoryDataRepository categoryDataRepository;
    @Override
    public CategoryDTO convert(CategoryRequestUpdate request) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findById(request.getId());
        CategoryDTO categoryDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        categoryDTO.setTitle(request.getTitle());
        categoryDTO.setDescription(request.getDescription());
        return categoryDTO;
    }
}
