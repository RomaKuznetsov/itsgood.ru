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

    @PostMapping(value = "/createAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> createHibernateAddress(@Validated @RequestBody AddressRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.createHibernateAddress(request), HttpStatus.OK);
    }

    @PatchMapping(value = "/updateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> updateHibernateAddress(@Validated @RequestBody AddressRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.updateHibernateAddress(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> findHibernateAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findHibernateAddressById(request.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        addressDataService.deleteHibernateAddressById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findListAddressRegistration", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateAddress>> findListHibernateAddressByCode() {
        return new ResponseEntity<>(addressDataService.findListHibernateAddressRegistration(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateAddress>> findAllHibernateAddress() {
        return new ResponseEntity<>(addressDataService.findAllHibernateAddress(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAddress", consumes = {"application/xml", "application/json"})
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
        return new ResponseEntity<>( addressDataService.findSetHibernateDelivery(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findSetHibernateDelivery", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateAddress> findHibernateAddressByCustomerAuthenticateAndRegistration() {
        return new ResponseEntity<>(addressDataService.findHibernateAddressByCustomerAuthenticateAndRegistration(), HttpStatus.OK);
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
