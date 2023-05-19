package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.controller.request.customer.CustomerRequestSearch;
import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.domain.hibernate.*;
import com.itsgood.ru.service.spring.CustomerDataService;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/customer")
@RequiredArgsConstructor
public class CustomerDataController {

    private final CustomerDataService customerDataService;
    private final PasswordEncoder passwordEncoder;

    private final JWTConfiguration jwtConfiguration;

    private final CustomerDataRepository customerDataRepository;

    //ok
    @GetMapping(value = "/findAllHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<CustomerDTO>> findAllHibernateCustomer() {
        List<CustomerDTO> customers = customerDataService.findAllHibernateCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //ok
    @PostMapping(value = "/createHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> createHibernateCustomer(@Validated @RequestBody CustomerRequestCreate request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        CustomerDTO customerDTO = customerDataService.createHibernateCustomer(request);

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    //ok
    @PatchMapping(value = "/updateHibernateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> updateHibernateCustomer(@Validated @RequestBody CustomerRequestUpdate request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.updateHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> findHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findHibernateCustomerById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> findHibernateCustomerByMail (@Validated @RequestBody CustomerRequestSearch request,
                                                                    BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findHibernateCustomerByMail(request.getMail()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerById (@Validated @RequestBody CustomerRequestSearch request,
                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteHibernateCustomerById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerByAuthentication", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerByAuthentication () {
        customerDataService.deleteHibernateCustomerByAuthenticationInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCustomerByMail (@Validated @RequestBody CustomerRequestSearch request,
                                                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteHibernateCustomerByMail(request.getMail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findRolesHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<RoleDTO>> findRolesHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllRolesHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findRolesHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<RoleDTO>> findRolesHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllRolesHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findContractsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ContractDTO>> findContractsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                               BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findContractsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ContractDTO>> findContractsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<AddressDTO>> findAddressHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                            BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllAddressHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAddressHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<AddressDTO>> findAddressHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllAddressHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<PaymentDTO>> findPaymentsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                             BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllPaymentsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findPaymentsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<PaymentDTO>> findPaymentsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllPaymentsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllContractsHibernateCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ContractDTO>> findAllContractsHibernateCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                                  BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerById(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllContractsHibernateCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ContractDTO>> findAllContractsHibernateCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllContractsHibernateCustomerByAuthenticate(), HttpStatus.OK);
    }

    @PutMapping("/passwords")
    public ResponseEntity<Object> updateUsersPasswords() {

        List<CustomerDTO> all = customerDataService.findAllHibernateCustomer();

        for (CustomerDTO customer : all) {
            AuthenticationInfo authenticationInfo = customer.getAuthenticationInfo();
            String password = authenticationInfo.getPassword();
            String encodedPassword = passwordEncoder.encode(password + jwtConfiguration.getServerPasswordSalt());
            authenticationInfo.setPassword(encodedPassword);
            customer.setAuthenticationInfo(authenticationInfo);
            customerDataRepository.save(customer);
        }
        return new ResponseEntity<>(all.size(), HttpStatus.OK);

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
