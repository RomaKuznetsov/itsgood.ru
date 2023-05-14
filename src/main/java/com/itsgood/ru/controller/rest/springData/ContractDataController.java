package com.itsgood.ru.controller.rest.springData;

import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestSearch;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.controller.service.ContractDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/contract")
@RequiredArgsConstructor
public class ContractDataController {

    private final ContractDataService contractDataService;

    @GetMapping(value = "/findAllHibernateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateContract>> findAllHibernateContract() {
        return new ResponseEntity<>(contractDataService.findAllHibernateContract(), HttpStatus.OK);
    }


    @GetMapping(value = "/findHibernateContractById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateContract> findHibernateContractById(@Validated @RequestBody ContractRequestSearch request,
                                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.findHibernateContractById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateContractByCustomerAuthenticateRelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateContract> findHibernateContractByCustomerAuthenticateRelevance() {
        return new ResponseEntity<>(contractDataService.findHibernateContractByCustomerAuthenticateRelevance(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateContract> createHibernateContract(@Validated @RequestBody ContractRequestCreate request,
                                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.createHibernateContract(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateHibernateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateContract> updateHibernateContract(@Validated @RequestBody ContractRequestUpdate request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.updateHibernateContract(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateContractByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateContractByAuthenticate(@Validated @RequestBody ContractRequestUpdate request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        contractDataService.deleteHibernateContractByAuthenticate(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateContractById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateContractById(@Validated @RequestBody ContractRequestUpdate request,
                                                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        contractDataService.deleteHibernateContractById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetHibernateContract_itemsByCustomerAuthenticateAndRelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract_item>> findSetHibernateContract_itemsByCustomerAuthenticateAndRelevance() {
        return new ResponseEntity<>(contractDataService.findSetHibernateContract_itemsByCustomerAuthenticateAndRelevance(), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetItemsByCustomerAuthenticateAndRelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateItem>> findSetItemsByCustomerAuthenticateAndRelevance() {
        return new ResponseEntity<>(contractDataService.findSetItemsByCustomerAuthenticateAndRelevance(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/findSetHibernateContracts_items", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateContract_item>> deleteHibernateContractById(@Validated @RequestBody ContractRequestSearch request,
                                                              BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        };
        return new ResponseEntity<>(contractDataService.findSetHibernateContracts_items(request.getId()), HttpStatus.OK);
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
