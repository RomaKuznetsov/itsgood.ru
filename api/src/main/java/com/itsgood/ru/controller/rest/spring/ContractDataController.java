package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.contract.ContractRequestSearch;
import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.service.spring.ContractDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
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
    public ResponseEntity<List<ContractDTO>> findAllHibernateContract() {
        return new ResponseEntity<>(contractDataService.findAllHibernateContract(), HttpStatus.OK);
    }


    @GetMapping(value = "/findHibernateContractById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findHibernateContractById(@Validated @RequestBody ContractRequestSearch request,
                                                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.findHibernateContractById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateContractByCustomerAuthenticateRelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findHibernateContractByCustomerAuthenticateRelevance() {
        return new ResponseEntity<>(contractDataService.findHibernateContractByAuthenticateAndRelevance(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> createHibernateContract(@Validated @RequestBody ContractRequestCreate request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.createHibernateContract(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/updateHibernateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> updateHibernateContract(@Validated @RequestBody ContractRequestUpdate request,
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
    public ResponseEntity<Set<Contract_itemDTO>> findSetHibernateContract_itemsByCustomerAuthenticateAndRelevance() {
        return new ResponseEntity<>(contractDataService.findSetHibernateContract_itemsByAuthenticateAndRelevance(), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetItemsByCustomerAuthenticateAndRelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findSetItemsByCustomerAuthenticateAndRelevance() {
        return new ResponseEntity<>(contractDataService.findSetItemsByCustomerAuthenticateAndRelevance(), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetHibernateContract_item", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<Contract_itemDTO>> findSetHibernateContract_item(@Validated @RequestBody ContractRequestSearch request,
                                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.findSetHibernateContract_item(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllHibernateContractsByAuthenticateAndIrrelevance", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ContractDTO>> findAllHibernateContractsByAuthenticateAndIrrelevance() {
        return new ResponseEntity<>(contractDataService.findAllHibernateContractsByAuthenticateAndIrrelevance(), HttpStatus.OK);
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
