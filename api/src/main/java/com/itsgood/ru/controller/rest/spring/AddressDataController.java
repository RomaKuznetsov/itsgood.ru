package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.configuration.HttpRequestConfiguration;
import com.itsgood.ru.controller.request.address.AddressRequestCreate;
import com.itsgood.ru.controller.request.address.AddressRequestSearch;
import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.domain.hibernate.AddressDTO;
import com.itsgood.ru.domain.hibernate.EquipmentDTO;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.security.util.CustomHeaders;
import com.itsgood.ru.service.spring.AddressDataService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/spring/address")
@RequiredArgsConstructor
public class AddressDataController extends HttpHeaders {
    private final AddressDataService addressDataService;

    private final HttpRequestConfiguration configuration;

    @PostMapping(value = "/createAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<AddressDTO> createAddress(@Validated @RequestBody AddressRequestCreate request, BindingResult result) {
        String tor = String.valueOf(configuration.getHeaders().get(CustomHeaders.X_AUTH_TOKEN));
        String head = tor;
        String hui = String.valueOf(super.get(CustomHeaders.X_AUTH_TOKEN));
        String chlen = hui;
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.createAddress(request), HttpStatus.OK);
    }

    @PutMapping(value = "/updateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<AddressDTO> updateAddress(@AuthenticationPrincipal Jwt jwt, @Validated @RequestBody AddressRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.updateHibernateAddress(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<AddressDTO> findAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findAddressById(request.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteAddressById(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        addressDataService.deleteAddressById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @GetMapping(value = "/findListAddressByCode", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<AddressDTO>> findListAddressByCode() {
        return new ResponseEntity<>(addressDataService.findListAddressRegistration(), HttpStatus.OK);
    }

    //ok
    @GetMapping(value = "/findAllAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<AddressDTO>> findAllAddress() {
        return new ResponseEntity<>(addressDataService.findAllAddress(), HttpStatus.OK);
    }

    //ok
    @DeleteMapping(value = "/deleteHibernateAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteAddress(@Validated @RequestBody AddressRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        addressDataService.deleteAddress(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findSetEquipment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<EquipmentDTO>> findSetEquipment(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findSetEquipment(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressByCustomerAuthenticateAndRegistration", consumes = {"application/xml", "application/json"})
    public ResponseEntity<AddressDTO> findAddressByCustomerAuthenticateAndRegistration() {
        return new ResponseEntity<>(addressDataService.findAddressByAuthenticateAndRegistration(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllAddressByAuthenticateAndCode", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<AddressDTO>> findAllAddressByAuthenticateAndCode(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findAllAddressByAuthenticateAndCode(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findListEquipmentOneAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<EquipmentDTO>> findListEquipmentOneAddress(@Validated @RequestBody AddressRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(addressDataService.findListEquipmentOneAddress(request), HttpStatus.OK);
    }
}

