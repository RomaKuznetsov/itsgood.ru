package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.customer.CustomerRequestCreate;
import com.itsgood.ru.controller.request.customer.CustomerRequestSearch;
import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.*;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CustomerDataService {

    private final AuthenticationInfo authenticationInfo;
    private final CustomerDataRepository customerDataRepository;
    private final CustomerConverterRequestCreate customerConverterRequestCreate;
    private final CustomerConverterRequestUpdate customerConverterRequestUpdate;
    //    @Cacheable("customer")
    public List<CustomerDTO> findAllHibernateCustomer() {
        return customerDataRepository.findAll();
    }

    public CustomerDTO findHibernateCustomerById(Integer id) {
        Optional<CustomerDTO> searchResult = customerDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CustomerDTO findHibernateCustomerByMail(String mail) {
        Optional<CustomerDTO> searchResult = customerDataRepository.
                findByMail(mail);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public CustomerDTO findHibernateCustomerByAuthenticationInfo() {
        Optional<CustomerDTO> searchResult = customerDataRepository.
                findByAuthenticationInfoUsername(authenticationInfo.getUsername());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CustomerDTO createHibernateCustomer(CustomerRequestCreate request) {
        CustomerDTO customerDTO = findHibernateCustomerByMail(request.getMail());
        if (!customerDTO.getAuthenticationInfo().getUsername().equals(request.getUsername())) {
            customerDTO = customerDataRepository.save(
                    customerConverterRequestCreate.convert(request));
        } else throw new EntityExistsException();
        return customerDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public CustomerDTO updateHibernateCustomerById(CustomerRequestUpdate request) {
        CustomerDTO customerDTO = findHibernateCustomerByAuthenticationInfo();
        if (request.getId() == customerDTO.getId()) {
            customerDTO = customerDataRepository.save(customerConverterRequestUpdate.convert(request));
        } //ебалово
        return customerDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateCustomerByMail(String mail) {
        customerDataRepository.delete(findHibernateCustomerByMail(mail));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateCustomerById(Integer id) {
        customerDataRepository.delete(findHibernateCustomerById(id));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateCustomerByAuthenticationInfo() {
        customerDataRepository.delete(findHibernateCustomerByAuthenticationInfo());
    }

    @Cacheable("role")
    public Set<RoleDTO> findAllRolesHibernateCustomerByAuthenticate() {
        CustomerDTO customerDTO = findHibernateCustomerByAuthenticationInfo();
        Set<RoleDTO> roles = customerDTO.getRoles();
        return roles;
    }
    @Cacheable("role")
    public Set<RoleDTO> findAllRolesHibernateCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findHibernateCustomerById(request.getId());
        Set<RoleDTO> roles = customerDTO.getRoles();
        return roles;
    }

    @Cacheable("address")
    public Set<AddressDTO> findAllAddressHibernateCustomerByAuthenticate() {
        CustomerDTO customerDTO = findHibernateCustomerByAuthenticationInfo();
        Set<AddressDTO> addresses = customerDTO.getAddress();
        return addresses;
    }
    @Cacheable("address")
    public Set<AddressDTO> findAllAddressHibernateCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findHibernateCustomerById(request.getId());
        Set<AddressDTO> addresses = customerDTO.getAddress();
        return addresses;
    }
    @Cacheable("payment")
    public Set<PaymentDTO> findAllPaymentsHibernateCustomerByAuthenticate() {
        CustomerDTO customerDTO = findHibernateCustomerByAuthenticationInfo();
        Set<PaymentDTO> payments = customerDTO.getPayments();
        return payments;
    }

    @Cacheable("payment")
    public Set<PaymentDTO> findAllPaymentsHibernateCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findHibernateCustomerById(request.getId());
        Set<PaymentDTO> payments = customerDTO.getPayments();
        return payments;
    }
    @Cacheable("contract")
    public Set<ContractDTO> findAllContractsHibernateCustomerByAuthenticate() {
        CustomerDTO customerDTO = findHibernateCustomerByAuthenticationInfo();
        Set<ContractDTO> contracts = customerDTO.getContracts();
        return contracts;
    }
    @Cacheable("contract")
    public Set<ContractDTO> findAllContractsHibernateCustomerById(CustomerRequestSearch request) {
        CustomerDTO customerDTO = findHibernateCustomerById(request.getId());
        Set<ContractDTO> contracts = customerDTO.getContracts();
        return contracts;
    }
}
