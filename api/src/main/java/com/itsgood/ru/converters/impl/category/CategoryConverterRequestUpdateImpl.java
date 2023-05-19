package com.itsgood.ru.converters.impl.category;

import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateCategory;
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
    public HibernateCategory convert(CategoryRequestUpdate request) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(request.getId());
        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateCategory.setTitle(request.getTitle());
        hibernateCategory.setDescription(request.getDescription());
        return hibernateCategory;
    }
}
