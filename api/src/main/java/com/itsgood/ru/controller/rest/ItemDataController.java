package com.itsgood.ru.controller.rest;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.controller.request.item.ItemRequestSearch;
import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.service.ItemDataService;
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
@RequestMapping("/rest/spring/item")
@RequiredArgsConstructor
public class ItemDataController {

    private final ItemDataService itemDataService;

    @GetMapping(value = "/findAllItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findAllItem() {
        return new ResponseEntity<>(itemDataService.findAllHibernateItem(), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByIdOrTitle", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findItemByIdOrTitle(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByIdOrTitle(request), HttpStatus.OK);
    }

    @PostMapping(value = "/createItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> createItem(@Validated @RequestBody ItemRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.createItem(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/createItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> updateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.updateItem(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteItem(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteItemById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetBucketById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<BucketDTO>> findSetBucketById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findSetBucketById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByTitleAndPriceAfterOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findByTitleAndPriceAfterOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndPriceAfterOrFirm(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByTitleAndPriceBeforeOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findItemByTitleAndPriceBeforeOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndPriceBeforeOrFirm(request), HttpStatus.OK);
    }
    @GetMapping(value = "/findItemByTitleAndDescription", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findItemByTitleAndDescription(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndDescription(request), HttpStatus.OK);
    }
}
