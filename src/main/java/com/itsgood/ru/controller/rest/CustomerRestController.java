package com.itsgood.ru.controller.rest;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.service.CustomerAggregationService;
import com.itsgood.ru.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/rest/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerAggregationService customerAggregationService;

    private final CustomerService customerService;

    @PostMapping(value = "/createCustomers", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Customer> createCustomers(@RequestBody Customer request) {
        Customer response;
        try {
            response = customerService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OK
    @GetMapping(value = "/findAllCustomer")
    public ResponseEntity<Object> findAllCustomer() throws SQLException {
        List<Customer> listAllCustomer;
        try {
            listAllCustomer = customerAggregationService.createListAllCustomer();
            return new ResponseEntity<>(listAllCustomer, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OK
    @GetMapping(value = "/findCustomerByUsernameMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findCustomerByUsernameMail(@RequestBody Customer customer) {
        Customer response;
        try {
            response = customerService.findCustomer(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OK
    @PutMapping(value = "/updateCustomerByUsernameMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> updateCustomerByUsernameMail(@RequestBody Customer customer) {
        Customer response;
        try {
            response = customerService.update(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OK
    @GetMapping(value = "/findCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findCustomerById(@RequestBody Customer customer) {
        Customer response;
        try {
            response = customerService.findOne(customer.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OK
    @DeleteMapping(value = "/deleteCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCustomerById(@RequestBody Customer customer) {
        try {
            customerService.delete(customer.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
