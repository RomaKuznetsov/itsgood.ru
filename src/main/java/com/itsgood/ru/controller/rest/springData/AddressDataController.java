package com.itsgood.ru.controller.rest.springData;

import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestCreate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestSearch;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.controller.service.AddressDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/address")
@RequiredArgsConstructor
public class AddressDataController {
    private final AddressDataService addressDataService;

    @PostMapping(value = "/createHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> createHibernateAddress(@Validated @RequestBody AddressRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.createHibernateAddress(request), HttpStatus.OK);
    }

    @PutMapping(value = "/updateHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> updateHibernateAddress(@Validated @RequestBody AddressRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.updateHibernateAddress(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> findHibernateAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findHibernateAddressById(request.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        addressDataService.deleteHibernateAddressById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findListHibernateAddressByCode", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateAddress>> findListHibernateAddressByCode() {
        return new ResponseEntity<>(addressDataService.findListHibernateAddressRegistration(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateAddress>> findAllHibernateAddress() {
        return new ResponseEntity<>(addressDataService.findAllHibernateAddress(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateAddress(@Validated @RequestBody AddressRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        addressDataService.deleteHibernateAddress(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetHibernateDelivery", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<HibernateDelivery>> findSetHibernateDelivery(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findSetHibernateDelivery(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateAddressByCustomerAuthenticateAndRegistration", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> findHibernateAddressByCustomerAuthenticateAndRegistration() {
        return new ResponseEntity<>(addressDataService.findHibernateAddressByAuthenticateAndRegistration(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllHibernateAddressByAuthenticateAndCode", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateAddress>> findAllHibernateAddressByAuthenticateAndCode(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findAllHibernateAddressByAuthenticateAndCode(request), HttpStatus.OK);
    }
}

