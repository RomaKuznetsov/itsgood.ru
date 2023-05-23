package com.itsgood.ru.service.spring;

import com.itsgood.ru.configuration.HttpRequestConfiguration;
import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.controller.request.customer.CustomerRequestSearch;
import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.*;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.security.jwt.TokenProvider;
import com.itsgood.ru.security.util.CustomHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CustomerDataService {
    private final TokenProvider tokenProvider;
    private final HttpRequestConfiguration httpRequestConfiguration;
    private final CustomerDataRepository customerDataRepository;
    private final CustomerConverterRequestCreate customerConverterRequestCreate;
    private final CustomerConverterRequestUpdate customerConverterRequestUpdate;
    @Cacheable("customer")
    public List<CustomerDTO> findAllCustomer() {
        return customerDataRepository.findAll();
    }

    public CustomerDTO findCustomerById(Integer id) {
        Optional<CustomerDTO> searchResult = customerDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CustomerDTO findCustomerByMail(String mail) {
        Optional<CustomerDTO> searchResult = customerDataRepository.findByMail(mail);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CustomerDTO findCustomerByAuthenticationInfo() {
        String masToken = String.valueOf(httpRequestConfiguration.getHeaders().get(CustomHeaders.X_AUTH_TOKEN));
        String token = masToken.replace("[", "").replace("]", "");
        String mail = tokenProvider.getUsernameFromToken(token);
        Optional<CustomerDTO> searchResult = customerDataRepository.findByMail(mail);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CustomerDTO createCustomer(CustomerRequestCreate request) {
        CustomerDTO customerDTO = customerConverterRequestCreate.convert(request);
        return customerDataRepository.save(customerDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CustomerDTO updateCustomerById(CustomerRequestUpdate request) {
        CustomerDTO customerDTO = customerConverterRequestUpdate.convert(request);
        return customerDataRepository.save(customerDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomerByMail(String mail) {
        customerDataRepository.delete(findCustomerByMail(mail));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomerById(Integer id) {
        customerDataRepository.delete(findCustomerById(id));
    }
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomerByAuthenticationInfo() {
        customerDataRepository.delete(findCustomerByAuthenticationInfo());
    }
    public Set<AddressDTO> findAllAddressCustomerByAuthenticate() {
        CustomerDTO customerDTO = findCustomerByAuthenticationInfo();
        Set<AddressDTO> addresses = customerDTO.getAddress();
        return addresses;
    }
    public Set<AddressDTO> findAllAddressCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findCustomerById(request.getId());
        Set<AddressDTO> addresses = customerDTO.getAddress();
        return addresses;
    }
    public Set<PaymentDTO> findAllPaymentCustomerByAuthenticate() {
        CustomerDTO customerDTO = findCustomerByAuthenticationInfo();
        Set<PaymentDTO> payments = customerDTO.getPayments();
        return payments;
    }
    public Set<PaymentDTO> findAllPaymentsCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findCustomerById(request.getId());
        Set<PaymentDTO> payments = customerDTO.getPayments();
        return payments;
    }
    @Cacheable("role")
    public Set<RoleDTO> findAllRolesCustomerByAuthenticate() {
        CustomerDTO customerDTO = findCustomerByAuthenticationInfo();
        Set<RoleDTO> roles = customerDTO.getRoles();
        return roles;
    }
    @Cacheable("role")
    public Set<RoleDTO> findAllRolesCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findCustomerById(request.getId());
        Set<RoleDTO> roles = customerDTO.getRoles();
        return roles;
    }
    @Cacheable("contract")
    public ContractDTO findContractCustomerByAuthenticate() {
        CustomerDTO customerDTO = findCustomerByAuthenticationInfo();
        return customerDTO.getContract();
    }

    @Cacheable("contract")
    public ContractDTO findContractCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findCustomerById(request.getId());
        return customerDTO.getContract();
    }
}
