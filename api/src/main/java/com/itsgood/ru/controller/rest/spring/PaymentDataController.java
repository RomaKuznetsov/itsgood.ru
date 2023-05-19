package com.itsgood.ru.controller.rest.spring;


import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.controller.request.payment.PaymentRequestSearch;
import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernatePayment;
import com.itsgood.ru.service.spring.PaymentDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/springData/payment")
@RequiredArgsConstructor
public class PaymentDataController {

    private final PaymentDataService paymentDataService;


    @GetMapping(value = "/findAllHibernatePayments", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernatePayment>> findAllHibernatePayments() {
        return new ResponseEntity<>(paymentDataService.findAllHibernatePayments(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernatePayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernatePayment> createHibernatePayment(@Validated @RequestBody PaymentRequestCreate request,
                                                                   BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.createHibernatePayment(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernatePayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernatePayment(@Validated @RequestBody PaymentRequestUpdate request,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        paymentDataService.deleteHibernatePayment(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/updateHibernatePayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernatePayment> updateHibernatePayment(@Validated @RequestBody PaymentRequestUpdate request,
                                                                   BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.updateHibernatePayment(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernatePaymentByCustomerAndValidity", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernatePayment>> findHibernatePaymentByCustomerAndValidity(@Validated @RequestBody PaymentRequestSearch request,
                                                                                            BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.findHibernatePaymentByAuthenticateAndValidity(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernatePaymentByCustomerAndStatus", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernatePayment>> findHibernatePaymentByCustomerAndStatus(@Validated @RequestBody PaymentRequestSearch request,
                                                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.findHibernatePaymentByAuthenticateAndStatus(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernatePaymentById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernatePayment> findHibernatePaymentById(@Validated @RequestBody PaymentRequestSearch request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.findHibernatePaymentById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernatePaymentByAuthenticateAndActive", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernatePayment> findHibernatePaymentByAuthenticateAndActive() {
        return new ResponseEntity<>(paymentDataService.findHibernatePaymentByAuthenticateAndStatusActive(), HttpStatus.OK);
    }


//     //Domain - Car
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
}
