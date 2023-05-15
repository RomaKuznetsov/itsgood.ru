//package com.itsgood.ru.controller.rest.springData;
//
//import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestCreate;
//import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestSearch;
//import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestUpdate;
//import com.itsgood.ru.controller.service.DeliveryDataService;
//import com.itsgood.ru.exceptions.IllegalRequestException;
//import com.itsgood.ru.hibernate.domain.HibernateDelivery;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rest/springData/delivery")
//@RequiredArgsConstructor
//public class DeliveryDataController {
//
//    private final DeliveryDataService deliveryDataService;
//
//    @GetMapping(value = "/findHibernateDeliveryById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateDelivery> findHibernateCustomerById(@Validated @RequestBody DeliveryRequestSearch request,
//                                                                       BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(deliveryDataService.findHibernateDeliveryById(request.getId()), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/findAll", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<List<HibernateDelivery>> findAll() {
//        return new ResponseEntity<>(deliveryDataService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/createHibernateDelivery", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateDelivery> createHibernateDelivery(@Validated @RequestBody DeliveryRequestCreate request,
//                                                                     BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(deliveryDataService.createHibernateDelivery(request), HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "/updateHibernateDelivery", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<HibernateDelivery> updateHibernateDelivery(@Validated @RequestBody DeliveryRequestUpdate request,
//                                                                     BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        return new ResponseEntity<>(deliveryDataService.updateHibernateDelivery(request), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateDelivery", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateDelivery(@Validated @RequestBody DeliveryRequestUpdate request,
//                                                                     BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        deliveryDataService.deleteHibernateDelivery(request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteHibernateDeliveryById", consumes = {"application/xml", "application/json"})
//    public ResponseEntity<Object> deleteHibernateDeliveryById(@Validated @RequestBody DeliveryRequestUpdate request,
//                                                          BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalRequestException(result);
//        }
//        deliveryDataService.deleteHibernateDeliveryById(request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //Domain - Car
//    //GET + /rest/cars - findAll
//    //GET + /rest/cars/{id} - findOne
//    //POST + /rest/cars - create object
//    //PUT + /rest/cars - update object
//    //DELETE + /rest/cars - update object
//
//    //PATCH + /rest/cars  - partial update of object
//    //GET + /rest/cars/search
//    //GET + /rest/cars/search
//    //PUT + /rest/cars/calculate
//    //query - поисковой запрос
//    //limit/offset = page = ограничение на число выводимых объектов
//}
