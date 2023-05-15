//package com.itsgood.ru.controller.service;
//
//import com.itsgood.ru.controller.dto.converters.CategoryConverterRequestCreate;
//import com.itsgood.ru.controller.dto.converters.CategoryConverterRequestUpdate;
//import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestCreate;
//import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestSearch;
//import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestUpdate;
//import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestSearch;
//import com.itsgood.ru.controller.springDataRepository.CategoryDataRepository;
//import com.itsgood.ru.hibernate.domain.HibernateCategory;
//import com.itsgood.ru.hibernate.domain.HibernateItem;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.springframework.transaction.annotation.Isolation.DEFAULT;
//import static org.springframework.transaction.annotation.Propagation.REQUIRED;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryDataService {
//
//    private final CategoryDataRepository categoryDataRepository;
//    private final CategoryConverterRequestUpdate categoryConverterRequestUpdate;
//    private final CategoryConverterRequestCreate categoryConverterRequestCreate;
//
//    public HibernateCategory findHibernateCategoryById(Integer id) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(id);
//        return searchResult.orElseThrow(EntityNotFoundException::new);
//    }
//
//    public HibernateCategory findHibernateCategoryByTitle(String title) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitle(title);
//        return searchResult.orElseThrow(EntityNotFoundException::new);
//    }
//
//    public HibernateCategory findHibernateCategoryByTitleOrId(CategoryRequestSearch request) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitleOrId(request);
//        return searchResult.orElseThrow(EntityNotFoundException::new);
//    }
//
//
//    public List<HibernateCategory> findAllHibernateCategories() {
//        return categoryDataRepository.findAll();
//    }
//
//    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
//    public HibernateCategory createHibernateCategory(CategoryRequestCreate request) {
//        HibernateCategory hibernateCategory = categoryConverterRequestCreate.convert(request);
//        if (!findAllHibernateCategories().contains(hibernateCategory)) {
//            hibernateCategory = categoryDataRepository.save(hibernateCategory);
//        } else throw new EntityNotFoundException("Такая категория уже есть");
//        return hibernateCategory;
//    }
//
//    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
//    public HibernateCategory updateHibernateCategoryById(CategoryRequestUpdate request) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(request.getId());
//        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
//        if (hibernateCategory.getId() != 0) {
//            hibernateCategory = categoryDataRepository.save(categoryConverterRequestUpdate.convert(request));}
//        return hibernateCategory;
//    }
//
//    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
//    public void deleteHibernateCategoryByTitleOrId(CategoryRequestSearch request) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findHibernateCategoryByTitleOrId(request);
//        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
//        categoryDataRepository.deleteByTitleOrId(hibernateCategory);
//    }
//
//    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
//    public void deleteHibernateCategoryById(CategoryRequestUpdate request) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(request.getId());
//        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
//        categoryDataRepository.delete(hibernateCategory);
//    }
//
//    public Set<HibernateItem> findAllItemsHibernateCategoryById(Integer id) {
//        Optional<HibernateCategory> searchResult = categoryDataRepository.findById(id);
//        HibernateCategory hibernateCategory = searchResult.orElseThrow(EntityNotFoundException::new);
//        Set<HibernateItem> items = hibernateCategory.getItems();
//        return items;
//    }
//
//}
