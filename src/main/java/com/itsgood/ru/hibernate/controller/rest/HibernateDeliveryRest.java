package com.itsgood.ru.hibernate.controller.rest;

import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.hibernate.repository.HibernateDeliveryRepository;
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
@RequestMapping(value = "/rest/hibernate/delivery")
public class HibernateDeliveryRest {

    private final HibernateDeliveryRepository deliveryService;

    @GetMapping(value = "/findAllDeliveries", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllDeliveries() throws SQLException {
        List<HibernateDelivery> customers = deliveryService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
