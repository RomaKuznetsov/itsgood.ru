package com.itsgood.ru.controller.dto.converters.impl.category;

import com.itsgood.ru.controller.dto.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestUpdate;
import com.itsgood.ru.controller.service.CategoryDataService;
import com.itsgood.ru.controller.springDataRepository.CategoryDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
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
