package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.controller.request.customer.CustomerRequestSearch;
import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.domain.hibernate.*;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import com.itsgood.ru.service.spring.ContractDataService;
import com.itsgood.ru.service.spring.CustomerDataService;
import com.itsgood.ru.service.spring.RoleDataService;
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
@RequestMapping("/rest/spring/customer")
@RequiredArgsConstructor
public class CustomerDataController {
    private final PasswordEncoder passwordEncoder;
    private final JWTConfiguration jwtConfiguration;
    private final CustomerDataService customerDataService;
    private final RoleDataService roleDataService;
    private final ContractDataService contractDataService;
    private final CustomerDataRepository customerDataRepository;

    //ok
    @GetMapping(value = "/findAllCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<CustomerDTO>> findAllCustomer() {
        List<CustomerDTO> customers = customerDataService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //ok
    @PostMapping(value = "/createCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> createCustomer(@Validated @RequestBody CustomerRequestCreate request,
                                                      BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        CustomerDTO customerDTO = customerDataService.createCustomer(request);
        roleDataService.createRole(new RoleRequestCreate());
        contractDataService.createContract(new ContractRequestCreate());
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    //ok
    @PatchMapping(value = "/updateCustomer", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> updateCustomer(@Validated @RequestBody CustomerRequestUpdate request,
                                                      BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.updateCustomerById(request), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> findCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findCustomerById(request.getId()), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CustomerDTO> findCustomerByMail(@Validated @RequestBody CustomerRequestSearch request,
                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findCustomerByMail(request.getMail()), HttpStatus.OK);
    }

    //ok
    @DeleteMapping(value = "/deleteCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteCustomerById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCustomerByAuthentication", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCustomerByAuthentication() {
        customerDataService.deleteCustomerByAuthenticationInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //ok
    @DeleteMapping(value = "/deleteCustomerByMail", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCustomerByMail(@Validated @RequestBody CustomerRequestSearch request,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        customerDataService.deleteCustomerByMail(request.getMail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findRolesCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<RoleDTO>> findRolesCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                              BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllRolesCustomerById(request), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findRolesCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<RoleDTO>> findRolesCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllRolesCustomerByAuthenticate(), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findContractCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findContractCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findContractCustomerById(request), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findContractCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ContractDTO> findContractCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findContractCustomerByAuthenticate(), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findAddressCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<AddressDTO>> findAddressCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                   BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllAddressCustomerById(request), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findAddressCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<AddressDTO>> findAddressCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllAddressCustomerByAuthenticate(), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findPaymentsCustomerById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<PaymentDTO>> findPaymentsCustomerById(@Validated @RequestBody CustomerRequestSearch request,
                                                                    BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(customerDataService.findAllPaymentsCustomerById(request), HttpStatus.OK);
    }
    //ok
    @GetMapping(value = "/findPaymentsCustomerByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<PaymentDTO>> findPaymentsCustomerByAuthenticate() {
        return new ResponseEntity<>(customerDataService.findAllPaymentCustomerByAuthenticate(), HttpStatus.OK);
    }
    //ok
    @PutMapping("/passwords")
    public ResponseEntity<Object> updateUsersPasswords() {

        List<CustomerDTO> all = customerDataService.findAllCustomer();

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
