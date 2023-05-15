//package com.itsgood.ru.controller.rest.springData;
//
//import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestCreate;
//import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestSearch;
//import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestUpdate;
//import com.itsgood.ru.controller.service.ItemDataService;
//import com.itsgood.ru.exceptions.IllegalRequestException;
//import com.itsgood.ru.hibernate.domain.HibernateContract_item;
//import com.itsgood.ru.hibernate.domain.HibernateItem;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/rest/springData/item")
//@RequiredArgsConstructor
//public class ItemDataController {
//
//    private final ItemDataService itemDataService;
//
//    @GetMapping(value = "/findAllHibernateItem", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<List<HibernateItem>> findAllHibernateItem() {
//        return new ResponseEntity<>(itemDataService.findAllHibernateItem(), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/findHibernateItemById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateItem> findHibernateItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(itemDataService.findHibernateItemById(request.getId()), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/findHibernateItemByIdOrTitle", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateItem> findHibernateItemByIdOrTitle(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(itemDataService.findHibernateItemByIdOrTitle(request), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/createHibernateItem", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateItem> createHibernateItem(@Validated @RequestBody ItemRequestCreate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(itemDataService.createHibernateItem(request), HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "/createHibernateItem", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateItem> updateHibernateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(itemDataService.updateHibernateItem(request), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateItem", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        itemDataService.deleteHibernateItem(request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateItemById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        itemDataService.deleteHibernateItemById(request.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/findSetHibernateContract_itemById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Set<HibernateContract_item>> findSetHibernateContract_itemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(itemDataService.findSetHibernateContract_itemById(request.getId()), HttpStatus.OK);
//    }
//}
