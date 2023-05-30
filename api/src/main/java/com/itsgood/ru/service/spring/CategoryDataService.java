package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.controller.request.category.CategoryRequestSearch;
import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.converters.CategoryConverterRequestCreate;
import com.itsgood.ru.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CategoryDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.repository.spring.CategoryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CategoryDataService {

    private final CategoryDataRepository categoryDataRepository;
    private final CategoryConverterRequestUpdate categoryConverterRequestUpdate;
    private final CategoryConverterRequestCreate categoryConverterRequestCreate;

    @Cacheable("category")
    public List<CategoryDTO> findAllCategories() {
        return categoryDataRepository.findAll();
    }

    public CategoryDTO findCategoryById(Integer id) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CategoryDTO findCategoryByTitle(String title) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findCategoryByTitle(title);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CategoryDTO findCategoryByTitleOrId(CategoryRequestSearch request) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findCategoryByTitleOrId(request.getTitle(), request.getId());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CategoryDTO createCategory(CategoryRequestCreate request) {
        CategoryDTO categoryDTO = categoryConverterRequestCreate.convert(request);
        List<CategoryDTO> categories = findAllCategories();
        if (!categories.contains(categoryDTO)) {
            categoryDTO = categoryDataRepository.save(categoryDTO);
        } else throw new EntityExistsException("This category already exists");
        return categoryDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CategoryDTO updateCategoryById(CategoryRequestUpdate request) {
        CategoryDTO categoryDTO = categoryConverterRequestUpdate.convert(request);
        return categoryDataRepository.save(categoryDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteCategoryByTitleOrId(CategoryRequestSearch request) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findCategoryByTitleOrId(request.getTitle(), request.getId());
        CategoryDTO categoryDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        categoryDataRepository.deleteByTitleOrId(categoryDTO.getTitle(), categoryDTO.getId());
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteCategoryById(CategoryRequestUpdate request) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findById(request.getId());
        CategoryDTO categoryDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        categoryDataRepository.delete(categoryDTO);
    }

    public Set<ItemDTO> findAllItemsCategoryById(Integer id) {
        Optional<CategoryDTO> searchResult = categoryDataRepository.findById(id);
        CategoryDTO categoryDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return categoryDTO.getItems();
    }

}
