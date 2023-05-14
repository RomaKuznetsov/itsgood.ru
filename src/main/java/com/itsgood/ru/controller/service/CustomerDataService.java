package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestCreate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestSearch;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestCreate;
import com.itsgood.ru.controller.springDataRepository.CustomerDataRepository;
import com.itsgood.ru.hibernate.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final UserDetails userDetails;
    private final CustomerDataRepository customerDataRepository;
    private final CustomerConverterRequestCreate customerConverterRequestCreate;
    private final CustomerConverterRequestUpdate customerConverterRequestUpdate;
    private final RoleDataService roleDataService;

    private final ContractDataService contractDataService;

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
        Optional<HibernateCustomer> searchResult =
                customerDataRepository.findByAuthenticationInfo(new AuthenticationInfo(userDetails.getUsername(),
                        userDetails.getPassword()));
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCustomer createHibernateCustomer(CustomerRequestCreate request) {
        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        HibernateCustomer searchCustomer = findHibernateCustomerByMail(request.getMail());
        if (!searchCustomer.getAuthenticationInfo().getUsername().equals(request.getUsername())) {
            hibernateCustomer = customerDataRepository.save(
                    customerConverterRequestCreate.convert(request));
            Set<HibernateRole> roles = hibernateCustomer.getRoles();
            roles.add(roleDataService.createHibernateRole(new RoleRequestCreate()));
            hibernateCustomer.setRoles(roles);
            Set<HibernateContract> contracts = hibernateCustomer.getContracts();
            contracts.add(contractDataService.createHibernateContract(new ContractRequestCreate()));
            hibernateCustomer.setContracts(contracts);
        } else new EntityNotFoundException("Пользователь с таким именем уже зарегестрирован");
        return hibernateCustomer;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateCustomer updateHibernateCustomerById(CustomerRequestUpdate request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        if (request.getId() == hibernateCustomer.getId()) {
            hibernateCustomer = customerDataRepository.save(customerConverterRequestUpdate.convert(request));
        }
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

    public Set<HibernateRole> findAllRolesHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        return roles;
    }

    public Set<HibernateRole> findAllRolesHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        return roles;
    }

    public Set<HibernateAddress> findAllAddressHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateAddress> addresses = hibernateCustomer.getAddress();
        return addresses;
    }

    public Set<HibernateAddress> findAllAddressHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateAddress> addresses = hibernateCustomer.getAddress();
        return addresses;
    }

    public Set<HibernatePayment> findAllPaymentsHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        return payments;
    }

    public Set<HibernatePayment> findAllPaymentsHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        return payments;
    }

    public Set<HibernateContract> findAllContractsHibernateCustomerByAuthenticate() {
        HibernateCustomer hibernateCustomer = findHibernateCustomerByAuthenticationInfo();
        Set<HibernateContract> contracts = hibernateCustomer.getContracts();
        return contracts;
    }

    public Set<HibernateContract> findAllContractsHibernateCustomerById(CustomerRequestSearch request) {
        HibernateCustomer hibernateCustomer = findHibernateCustomerById(request.getId());
        Set<HibernateContract> contracts = hibernateCustomer.getContracts();
        return contracts;
    }
}
