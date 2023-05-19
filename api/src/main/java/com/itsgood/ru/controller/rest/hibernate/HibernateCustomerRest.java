package com.itsgood.ru.controller.rest.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import com.itsgood.ru.service.hibernate.HibernateCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/hibernate/customer")
public class HibernateCustomerRest {
    private final HibernateCustomerService customerService;

    @GetMapping(value = "/findAllCustomers", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllCustomers() throws SQLException {
        List<HibernateCustomer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/saveCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> saveCustomer() throws SQLException {
        List<HibernateCustomer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
