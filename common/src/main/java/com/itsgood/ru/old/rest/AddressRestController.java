package com.itsgood.ru.old.rest;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.hibernate.AuthenticationInfo;
import com.itsgood.ru.old.service.AddressAggregationService;
import com.itsgood.ru.old.service.AddressService;
import com.itsgood.ru.old.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/rest/address")
@RequiredArgsConstructor
public class AddressRestController {
    private final AddressAggregationService addressAggregationService;
    private final AddressService addressService;
    private final CustomerService customerService;
    //OK
    @GetMapping(value = "/findCustomerRegistration", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Address> findCustomerRegistration(@RequestBody Customer customer) {
        Address address;
        try {
            address = addressService.findCustomerRegistration(customer);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping(value = "/findAllAddress", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllAddress() {

        try {
            return new ResponseEntity<>(addressAggregationService.findListAllAddress(),
                    HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/findListCustomerDelivery", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findListCustomerDelivery(@RequestBody Customer request) {
        try {
            return new ResponseEntity<>(addressAggregationService.findListCustomerDelivery(request),
                    HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/findListAddressOneCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findListAddressOneCustomer(@RequestBody Customer request) {
        try {
            return new ResponseEntity<>(addressAggregationService.findListAddressOneCustomer(request),
                    HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping(value = "/create", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> create(@RequestBody Address address) throws Exception {
        List<Address> listAllAddressOneCustomer;
        Customer findCustomer;
        try {
            findCustomer = customerService.findCustomerByMail("kuznecby@mail.ru");
            listAllAddressOneCustomer = addressAggregationService.findListAddressOneCustomer(findCustomer);
            address.setCustomer_id(findCustomer.getId());
            if (listAllAddressOneCustomer.size() == 0) {
                addressService.create(address);
            } else if (!listAllAddressOneCustomer.contains(address)) {
                addressService.create(address);
            } else System.out.println("this address already exists");
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> update(@RequestBody Address request) {
        Address address;
        try {
            address = addressService.update(request);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/findAddressById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAddressById(@RequestBody Address request) {
        Address address;
        try {
            address = addressService.findOne(request.getId());
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/deleteAddressById", consumes = {"application/xml", "application/json"})
    public void deleteAddressById(@RequestBody Address request) {
        try {
            addressService.delete(request.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
