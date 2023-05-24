package com.itsgood.ru.controller.rest;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.contract.ContractRequestSearch;
import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.service.ContractDataService;
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
@RequestMapping("/rest/spring/contract")
@RequiredArgsConstructor
public class ContractDataController {

    private final ContractDataService contractDataService;

    @GetMapping(value = "/findAllContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ContractDTO>> findAllContract() {
        return new ResponseEntity<>(contractDataService.findAllContract(), HttpStatus.OK);
    }


    @GetMapping(value = "/findContractById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findContractById(@Validated @RequestBody ContractRequestSearch request,
                                                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.findContractById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findContractByCustomerAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findContractByCustomerAuthenticate() {
        return new ResponseEntity<>(contractDataService.findContractByAuthenticate(), HttpStatus.OK);
    }

    @PostMapping(value = "/createContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> createContract(@Validated @RequestBody ContractRequestCreate request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.createContract(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/updateContract", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> updateContract(@Validated @RequestBody ContractRequestUpdate request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.updateContract(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteContractByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteContractByAuthenticate(@Validated @RequestBody ContractRequestUpdate request,
                                                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        contractDataService.deleteContractByAuthenticate(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteContractById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteContractById(@Validated @RequestBody ContractRequestUpdate request,
                                                              BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        contractDataService.deleteContractById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetBucketsByCustomerAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<BucketDTO>> findSetBucketsByCustomerAuthenticate() {
        return new ResponseEntity<>(contractDataService.findSetEquipmentsByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetItemsByCustomerAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findSetItemsByCustomerAuthenticate() {
        return new ResponseEntity<>(contractDataService.findSetItemsByCustomerAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetBuckets", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<BucketDTO>> findSetBuckets(@Validated @RequestBody ContractRequestSearch request,
                                                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(contractDataService.findSetBucket(), HttpStatus.OK);
    }
}
