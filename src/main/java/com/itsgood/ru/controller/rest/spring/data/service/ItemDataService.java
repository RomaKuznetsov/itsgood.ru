package com.itsgood.ru.controller.rest.spring.data.service;

import com.itsgood.ru.controller.rest.spring.converters.ItemConverterRequestCreate;
import com.itsgood.ru.controller.rest.spring.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestCreate;
import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestSearch;
import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.repository.ItemDataRepository;
import com.itsgood.ru.hibernate.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class ItemDataService {

    private final ItemDataRepository itemDataRepository;
    private final CategoryDataService categoryDataService;
    private final ItemConverterRequestCreate itemConverterRequestCreate;
    private final ItemConverterRequestUpdate itemConverterRequestUpdate;
    @Cacheable("item")
    public List<HibernateItem> findAllHibernateItem() {
        return itemDataRepository.findAll();
    }

    public HibernateItem findHibernateItemById(Integer id) {
        Optional<HibernateItem> searchResult = itemDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateItem findHibernateItemByIdOrTitle(ItemRequestSearch request) {
        Optional<HibernateItem> searchResult = itemDataRepository.findHibernateItemByIdOrTitle(request.getId(), request.getTitle());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateItem createHibernateItem(ItemRequestCreate request) {
        HibernateCategory hibernateCategory = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        HibernateItem hibernateItem = itemConverterRequestCreate.convert(request);
        Set<HibernateItem> searchResult = hibernateCategory.getItems();
        if (!searchResult.contains(hibernateItem)) {
            hibernateItem.setCategory(hibernateCategory);
            hibernateItem = itemDataRepository.save(hibernateItem);
        }
        return hibernateItem;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateItem updateHibernateItem(ItemRequestUpdate request) {
        HibernateCategory hibernateCategory = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        HibernateItem hibernateItem = itemConverterRequestUpdate.convert(request);
        Set<HibernateItem> searchResult = hibernateCategory.getItems();
        if (!searchResult.contains(hibernateItem)) {
            hibernateItem.setCategory(hibernateCategory);
            hibernateItem = itemDataRepository.save(hibernateItem);
        }
        return hibernateItem;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateItem(ItemRequestUpdate request) {
        HibernateCategory hibernateCategory = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        HibernateItem hibernateItem = itemConverterRequestUpdate.convert(request);
        Set<HibernateItem> searchResult = hibernateCategory.getItems();
        if (!searchResult.contains(hibernateItem)) {
            hibernateItem.setCategory(hibernateCategory);
            itemDataRepository.delete(hibernateItem);
        }
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateItemById(Integer id) {
        itemDataRepository.deleteById(id);
    }

    //    @Cacheable("contract_item")
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Set<HibernateContract_item> findSetHibernateContract_itemById(Integer id) {
        return findHibernateItemById(id).getContracts_items();
    }

    public List<HibernateItem> findHibernateItemByTitleAndPriceAfterOrFirm(ItemRequestSearch request) {
        Optional<List<HibernateItem>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceAfterOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<HibernateItem> findHibernateItemByTitleAndPriceBeforeOrFirm(ItemRequestSearch request) {
        Optional<List<HibernateItem>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceBeforeOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<HibernateItem> findHibernateItemByTitleAndDescription(ItemRequestSearch request) {
        Optional<List<HibernateItem>> searchResult = itemDataRepository.findHibernateItemByTitleAndDescription(request.getTitle(), request.getDescription());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

}

