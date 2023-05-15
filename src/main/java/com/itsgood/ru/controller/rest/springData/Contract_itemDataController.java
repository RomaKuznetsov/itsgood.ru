//package com.itsgood.ru.controller.rest.springData;
//
//import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestCreate;
//import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestSearch;
//import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestUpdate;
//import com.itsgood.ru.controller.service.Contract_itemDataService;
//import com.itsgood.ru.exceptions.IllegalRequestException;
//import com.itsgood.ru.hibernate.domain.HibernateContract_item;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/rest/springData/contract_itemController")
//@RequiredArgsConstructor
//public class Contract_itemDataController {
//
//    private final Contract_itemDataService contract_itemDataService;
//
//    @GetMapping(value = "/findHibernateContract_itemById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateContract_item> findHibernateContract_itemById(@Validated @RequestBody Contract_itemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(contract_itemDataService.findHibernateContract_itemById(request.getId()), HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "/findHibernateContract_itemsByCustomerAuthenticateAndRelevance", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Set<HibernateContract_item>> deleteHibernateCategoryByTitleOrId(@Validated @RequestBody
//                                                                                              Contract_itemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(contract_itemDataService.findHibernateContract_itemsByCustomerAuthenticateAndRelevance(request), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/createHibernateContract_item", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateContract_item> createHibernateContract_item(@Validated @RequestBody
//                                                                                    Contract_itemRequestCreate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(contract_itemDataService.createHibernateContract_item(request), HttpStatus.CREATED);
//
//    }
//
//    @PutMapping(value = "/updateHibernateContract_item", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateContract_item> updateHibernateContract_item(@Validated @RequestBody Contract_itemRequestUpdate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(contract_itemDataService.updateHibernateContract_item(request), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateContract_itemById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateContract_itemById(@Validated @RequestBody Contract_itemRequestSearch request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        contract_itemDataService.deleteHibernateContract_itemById(request.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateContract_item", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateContract_item(@Validated @RequestBody Contract_itemRequestUpdate request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        contract_itemDataService.deleteHibernateContract_item(request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // //Domain - Car
//    //    //GET + /rest/cars - findAll
//    //    //GET + /rest/cars/{id} - findOne
//    //    //POST + /rest/cars - create object
//    //    //PUT + /rest/cars - заменить, update object
//    //    //DELETE + /rest/cars - update object
//    //
//    //    //PATCH + /rest/cars  - partial update of object
//    //    //GET + /rest/cars/search
//    //    //GET + /rest/cars/search
//    //    //PUT + /rest/cars/calculate
//    //    //query - поисковой запрос
//    //    //limit/offset = page = ограничение на число выводимых объектов
//
//}
