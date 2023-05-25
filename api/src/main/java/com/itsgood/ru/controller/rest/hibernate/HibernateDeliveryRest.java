package com.itsgood.ru.controller.rest.hibernate;

import com.itsgood.ru.domain.EquipmentDTO;
import com.itsgood.ru.repository.hibernate.HibernateDeliveryRepository;
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
        List<EquipmentDTO> customers = deliveryService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
