package com.itsgood.ru.service;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.controller.request.item.ItemRequestSearch;
import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.converters.ItemConverterRequestCreate;
import com.itsgood.ru.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.domain.CategoryDTO;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.repository.ItemDataRepository;
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
public class ItemDataService {

    private final ItemDataRepository itemDataRepository;
    private final CategoryDataService categoryDataService;
    private final ItemConverterRequestCreate itemConverterRequestCreate;
    private final ItemConverterRequestUpdate itemConverterRequestUpdate;
    @Cacheable("item")
    public List<ItemDTO> findAllHibernateItem() {
        return itemDataRepository.findAll();
    }

    public ItemDTO findItemById(Integer id) {
        Optional<ItemDTO> searchResult = itemDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public ItemDTO findItemByIdOrTitle(ItemRequestSearch request) {
        Optional<ItemDTO> searchResult = itemDataRepository.findItemByIdOrTitle(request.getId(), request.getTitle());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ItemDTO createItem(ItemRequestCreate request) {
        CategoryDTO categoryDTO = categoryDataService.findCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestCreate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (!searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDTO = itemDataRepository.save(itemDTO);
        } else throw new EntityExistsException("This item already exists");
        return itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ItemDTO updateItem(ItemRequestUpdate request) {
        CategoryDTO categoryDTO = categoryDataService.findCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestUpdate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDTO = itemDataRepository.save(itemDTO);
        } else throw new EntityNotFoundException("There is no such item in this category");
        return itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteItem(ItemRequestUpdate request) {
        CategoryDTO categoryDTO = categoryDataService.findCategoryById(request.getCategory_id());
        ItemDTO itemDTO = itemConverterRequestUpdate.convert(request);
        Set<ItemDTO> searchResult = categoryDTO.getItems();
        if (searchResult.contains(itemDTO)) {
            itemDTO.setCategory(categoryDTO);
            itemDataRepository.delete(itemDTO);
        } else throw new EntityNotFoundException("There is no such item in this category");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteItemById(Integer id) {
        itemDataRepository.deleteById(id);
    }

    //    @Cacheable("bucket")
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Set<BucketDTO> findSetBucketById(Integer id) {
        return findItemById(id).getBuckets();
    }

    public List<ItemDTO> findItemByTitleAndPriceAfterOrFirm(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceAfterOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<ItemDTO> findItemByTitleAndPriceBeforeOrFirm(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndPriceBeforeOrFirm(request.getTitle(), request.getPrice(), request.getFirm());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<ItemDTO> findItemByTitleAndDescription(ItemRequestSearch request) {
        Optional<List<ItemDTO>> searchResult = itemDataRepository.findHibernateItemByTitleAndDescription(request.getTitle(), request.getDescription());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

}

