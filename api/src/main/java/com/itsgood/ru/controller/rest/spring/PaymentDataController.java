package com.itsgood.ru.controller.rest.spring;


import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.controller.request.payment.PaymentRequestSearch;
import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
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
@RequestMapping("/rest/spring/payment")
@RequiredArgsConstructor
public class PaymentDataController {

    private final PaymentDataService paymentDataService;


    @GetMapping(value = "/findAllPayments", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<PaymentDTO>> findAllPayments() {
        return new ResponseEntity<>(paymentDataService.findAllPayments(), HttpStatus.OK);
    }

    @PostMapping(value = "/createPayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentDTO> createPayment(@Validated @RequestBody PaymentRequestCreate request,
                                                             BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.createPayment(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deletePayment(@Validated @RequestBody PaymentRequestUpdate request,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        paymentDataService.deletePayment(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/updatePayment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentDTO> updatePayment(@Validated @RequestBody PaymentRequestUpdate request,
                                                             BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.updatePayment(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentByCustomerAndValidity", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<PaymentDTO>> findPaymentByCustomerAndValidity(@Validated @RequestBody PaymentRequestSearch request,
                                                                                      BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.findPaymentByAuthenticateAndValidity(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllPaymentsByAuthenticateAndStatusInactive", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<PaymentDTO>> findAllPaymentsByAuthenticateAndStatusInactive() {
        return new ResponseEntity<>(paymentDataService.findAllPaymentsByAuthenticateAndStatusInactive(), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentByAuthenticateAndStatusActive", consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentDTO> findPaymentByAuthenticateAndStatusActive() {
        return new ResponseEntity<>(paymentDataService.findPaymentByAuthenticateAndStatusActive(), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentDTO> findPaymentById(@Validated @RequestBody PaymentRequestSearch request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(paymentDataService.findPaymentById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentByAuthenticateAndActive", consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentDTO> findPaymentByAuthenticateAndActive() {
        return new ResponseEntity<>(paymentDataService.findPaymentByAuthenticateAndStatusActive(), HttpStatus.OK);
    }
}
