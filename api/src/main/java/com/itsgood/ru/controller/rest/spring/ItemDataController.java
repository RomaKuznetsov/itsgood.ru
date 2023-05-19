package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.controller.request.item.ItemRequestSearch;
import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.service.spring.ItemDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/item")
@RequiredArgsConstructor
public class ItemDataController {

    private final ItemDataService itemDataService;

    @GetMapping(value = "/findAllHibernateItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findAllHibernateItem() {
        return new ResponseEntity<>(itemDataService.findAllHibernateItem(), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findHibernateItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findHibernateItemById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateItemByIdOrTitle", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findHibernateItemByIdOrTitle(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findHibernateItemByIdOrTitle(request), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernateItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> createHibernateItem(@Validated @RequestBody ItemRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.createHibernateItem(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/createHibernateItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> updateHibernateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.updateHibernateItem(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteHibernateItem(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteHibernateItemById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetHibernateContract_itemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<Contract_itemDTO>> findSetHibernateContract_itemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findSetHibernateContract_itemById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateItemByTitleAndPriceAfterOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findHibernateItemByTitleAndPriceAfterOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findHibernateItemByTitleAndPriceAfterOrFirm(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateItemByTitleAndPriceBeforeOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findHibernateItemByTitleAndPriceBeforeOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findHibernateItemByTitleAndPriceBeforeOrFirm(request), HttpStatus.OK);
    }
    @GetMapping(value = "/findHibernateItemByTitleAndDescription", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findHibernateItemByTitleAndDescription(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findHibernateItemByTitleAndDescription(request), HttpStatus.OK);
    }

    //
    ////     Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceAfterOrFirm(String title, int price, String firm);
    ////    Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceBeforeOrFirm(String title, int price, String firm);
    ////    Optional<List<HibernateItem>> findHibernateItemByTitleAndDescription(String title, String description);
}
