package com.itsgood.ru.controller.rest.springData;


import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestSearch;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.service.CustomerDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.hibernate.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/customer")
@RequiredArgsConstructor
public class CustomerDataController {

    private final CustomerDataService customerDataService;

    //ok
    @GetMapping(value = "/findAllHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateCustomer>> findAllHibernateCustomer() {
        List<HibernateCustomer> customers = customerDataService.findAllHibernateCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //ok
    @PostMapping(value = "/createHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateCustomer> createHibernateCustomer(@Validated @RequestBody CustomerRequestCreate request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        HibernateCustomer hibernateCustomer = customerDataService.createHibernateCustomer(request);

        return new ResponseEntity<>(hibernateCustomer, HttpStatus.CREATED);
    }

    //ok
    @PutMapping(value = "/updateHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateCustomer> updateHibernateCustomer(@Validated @RequestBody CustomerRequestUpdate request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.updateHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateCustomer> findHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                            BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findHibernateCustomerById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateCustomer> findHibernateCustomerByMail (@Validated @RequestBody CustomerRequestSearch request,
                                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findHibernateCustomerByMail(request.getMail()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerById (@Validated @RequestBody CustomerRequestSearch request,
                                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteHibernateCustomerById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerByAuthentication", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerByAuthentication () {
        customerDataService.deleteHibernateCustomerByAuthenticationInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerByMail (@Validated @RequestBody CustomerRequestSearch request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteHibernateCustomerByMail(request.getMail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findRolesHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateRole>> findRolesHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllRolesHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findRolesHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateRole>> findRolesHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllRolesHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findContractsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract>> findContractsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                                  BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findContractsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract>> findContractsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateAddress>> findAddressHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                                BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllAddressHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateAddress>> findAddressHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllAddressHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernatePayment>> findPaymentsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                                BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllPaymentsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernatePayment>> findPaymentsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllPaymentsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllContractsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract>> findAllContractsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllContractsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract>> findAllContractsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    //Domain - Car
    //GET + /rest/cars - findAll
    //GET + /rest/cars/{id} - findOne
    //POST + /rest/cars - create object
    //PUT + /rest/cars - update object
    //DELETE + /rest/cars - update object

    //PATCH + /rest/cars  - partial update of object
    //GET + /rest/cars/search
    //GET + /rest/cars/search
    //PUT + /rest/cars/calculate
    //query - поисковой запрос
    //limit/offset = page = ограничение на число выводимых объектов


}
