package com.itsgood.ru.controller.rest.spring.data.service;

import com.itsgood.ru.controller.rest.spring.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.controller.rest.spring.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestSearch;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.repository.CustomerDataRepository;
import com.itsgood.ru.hibernate.domain.*;
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
    public List<HibernateCustomer> findAllHibernateCustomer() {
        return customerDataRepository.findAll();
    }

    public HibernateCustomer findHibernateCustomerById(Integer id) {
        Optional<HibernateCustomer> searchResult = customerDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateCustomer findHibernateCustomerByMail(String mail) {
        Optional<HibernateCustomer> searchResult = customerDataRepository.
                findByMail(mail);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateCustomer findHibernateCustomerByAuthenticationInfo() {
        Optional<HibernateCustomer> searchResult = customerDataRepository.
                findByAuthenticationInfoUsername(authenticationInfo.getUsername());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCustomer createHibernateCustomer(CustomerRequestCreate request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByMail(request.getMail());
        if (!hibernateCustomer.getAuthenticationInfo().getUsername().equals(request.getUsername())) {
            hibernateCustomer = customerDataRepository.save(
                    customerConverterRequestCreate.convert(request));
        } else throw new EntityExistsException();
        return hibernateCustomer;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCustomer updateHibernateCustomerById(CustomerRequestUpdate request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        if (request.getId() == hibernateCustomer.getId()) {
            hibernateCustomer = customerDataRepository.save(customerConverterRequestUpdate.convert(request));
        } //ебалово
        return hibernateCustomer;
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
    public Set<HibernateRole> findAllRolesHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        return roles;
    }
    @Cacheable("role")
    public Set<HibernateRole> findAllRolesHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        return roles;
    }

    @Cacheable("address")
    public Set<HibernateAddress> findAllAddressHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateAddress> addresses = hibernateCustomer.getAddress();
        return addresses;
    }
    @Cacheable("address")
    public Set<HibernateAddress> findAllAddressHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateAddress> addresses = hibernateCustomer.getAddress();
        return addresses;
    }
    @Cacheable("payment")
    public Set<HibernatePayment> findAllPaymentsHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        return payments;
    }

    @Cacheable("payment")
    public Set<HibernatePayment> findAllPaymentsHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        return payments;
    }
    @Cacheable("contract")
    public Set<HibernateContract> findAllContractsHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateContract> contracts = hibernateCustomer.getContracts();
        return contracts;
    }
    @Cacheable("contract")
    public Set<HibernateContract> findAllContractsHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateContract> contracts = hibernateCustomer.getContracts();
        return contracts;
    }
}
