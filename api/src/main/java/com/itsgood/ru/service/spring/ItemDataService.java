package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.controller.request.item.ItemRequestSearch;
import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.converters.ItemConverterRequestCreate;
import com.itsgood.ru.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CategoryDTO;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.repository.spring.ItemDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public List<ItemDTO> findAllHibernateItem() {
        return itemDataRepository.findAll();
    }

    public ItemDTO findHibernateItemById(Integer id) {
        Optional<ItemDTO> searchResult = itemDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public ItemDTO findHibernateItemByIdOrTitle(ItemRequestSearch request) {
        Optional<ItemDTO> searchResult = itemDataRepository.findHibernateItemByIdOrTitle(request.getId(), request.getTitle());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ItemDTO createHibernateItem(ItemRequestCreate request) {
        CategoryDTO categoryDTO = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestCreate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (!searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDTO = itemDataRepository.save(itemDTO);
        }
        return itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ItemDTO updateHibernateItem(ItemRequestUpdate request) {
        CategoryDTO categoryDTO = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestUpdate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (!searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDTO = itemDataRepository.save(itemDTO);
        }
        return itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateItem(ItemRequestUpdate request) {
        CategoryDTO categoryDTO = categoryDataService.findHibernateCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestUpdate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (!searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDataRepository.delete(itemDTO);
        }
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateItemById(Integer id) {
        itemDataRepository.deleteById(id);
    }

    //    @Cacheable("contract_item")
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Set<Contract_itemDTO> findSetHibernateContract_itemById(Integer id) {
        return findHibernateItemById(id).getContracts_items();
    }

    public List<ItemDTO> findHibernateItemByTitleAndPriceAfterOrFirm(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceAfterOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<ItemDTO> findHibernateItemByTitleAndPriceBeforeOrFirm(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceBeforeOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<ItemDTO> findHibernateItemByTitleAndDescription(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndDescription(request.getTitle(), request.getDescription());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

}

