package com.itsgood.ru.controller.rest.hibernate;

import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.service.hibernate.HibernateContractService;
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
@RequestMapping(value = "/rest/hibernate/contract")
public class HibernateContractRest {
    private final HibernateContractService contractService;

    @GetMapping(value = "/findAllContracts", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllContracts() throws SQLException {
            List<ContractDTO> contracts = contractService.findAll();
       return new ResponseEntity<>(contracts, HttpStatus.OK);
    }



}
