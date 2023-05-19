package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.controller.request.category.CategoryRequestSearch;
import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.converters.CategoryConverterRequestCreate;
import com.itsgood.ru.converters.CategoryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateCategory;
import com.itsgood.ru.domain.hibernate.HibernateItem;
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

    public HibernateCategory findHibernateCategoryById(Integer id) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateCategory findHibernateCategoryByTitle(String title) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitle(title);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateCategory findHibernateCategoryByTitleOrId(CategoryRequestSearch request) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitleOrId(request.getTitle(), request.getId());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Cacheable("category")
    public List<HibernateCategory> findAllHibernateCategories() {
        return categoryDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCategory createHibernateCategory(CategoryRequestCreate request) {
        HibernateCategory hibernateCategory = categoryConverterRequestCreate.convert(request);
        if (!findAllHibernateCategories().contains(hibernateCategory)) {
            hibernateCategory = categoryDataRepository.save(hibernateCategory);
        } else throw new EntityExistsException();
        return hibernateCategory;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCategory updateHibernateCategoryById(CategoryRequestUpdate request) {
        return categoryDataRepository.save(categoryConverterRequestUpdate.convert(request));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateCategoryByTitleOrId(CategoryRequestSearch request) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitleOrId(request.getTitle(), request.getId());
        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
        categoryDataRepository.deleteByTitleOrId(hibernateCategory.getTitle(), hibernateCategory.getId());
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateCategoryById(CategoryRequestUpdate request) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(request.getId());
        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
        categoryDataRepository.delete(hibernateCategory);
    }

    public Set<HibernateItem> findAllItemsHibernateCategoryById(Integer id) {
        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(id);
        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
        Set<HibernateItem> items = hibernateCategory.getItems();
        return items;
    }

}
