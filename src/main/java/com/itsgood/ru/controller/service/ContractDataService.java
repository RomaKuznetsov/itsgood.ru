package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.ContractConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestSearch;
import com.itsgood.ru.controller.springDataRepository.ContractDataRepository;
import com.itsgood.ru.enums.ContractRelevance;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import lombok.RequiredArgsConstructor;
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
public class ContractDataService {
    private final CustomerDataService customerDataService;
    private final ContractDataRepository contractDataRepository;
    private final ContractConverterRequestCreate contractConverterRequestCreate;
    private final ContractConverterRequestUpdate contractConverterRequestUpdate;

    private final PaymentDataService paymentDataService;

    public List<HibernateContract> findAllHibernateContract() {
        return contractDataRepository.findAll();
    }

    public HibernateContract findHibernateContractById(Integer id) {
        Optional<HibernateContract> searchResult = contractDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

//    public HibernateContract findHibernateContractByCustomerAuthenticateRelevance() {
//        HibernateContract hibernateContract = new HibernateContract();
//        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
//        hibernateContract.setCustomer(hibernateCustomer);
//        hibernateContract.setRelevance(ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
//        Optional<HibernateContract> searchResult = contractDataRepository.
//                findHibernateContractByCustomerAndRelevance(hibernateContract);
//        return searchResult.orElseThrow(EntityNotFoundException::new);
//    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateContract createHibernateContract(ContractRequestCreate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Set<HibernateContract> searchRequest = hibernateCustomer.getContracts();
        HibernateContract hibernateContract = contractConverterRequestCreate.convert(request);
        if (!searchRequest.contains(hibernateContract)) {
            if (request.getPayment_types().equals("cash")) {
                hibernateContract.setPayment(null);
            } else if (request.getPayment_types().equals("card")) {
                hibernateContract.setPayment(paymentDataService.findHibernatePaymentByAuthenticateAndActive());
            }
            hibernateContract.setCustomer(hibernateCustomer);
            contractDataRepository.save(hibernateContract);
        }
        return hibernateContract;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateContract updateHibernateContract(ContractRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Set<HibernateContract> searchRequest = hibernateCustomer.getContracts();
        HibernateContract hibernateContract = contractConverterRequestUpdate.convert(request);
        if (!searchRequest.contains(findHibernateContractById(request.getId()))) {
            List<HibernateItem> items = hibernateContract.getItems();
            hibernateContract.setSum_order(Math.toIntExact(items.stream().map(item -> item.getPrice()).count()));
            hibernateContract.setCustomer(hibernateCustomer);
            contractDataRepository.save(hibernateContract);
        }
        return hibernateContract;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContractByAuthenticate(ContractRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Set<HibernateContract> contracts = hibernateCustomer.getContracts();
        HibernateContract hibernateContract = findHibernateContractById(request.getId());
        if (!contracts.contains(hibernateContract)) {
            hibernateContract.setCustomer(hibernateCustomer);
            contractDataRepository.delete(hibernateContract);
        } else throw new EntityNotFoundException("Такого контракта у данного пользователя нет");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContractById(ContractRequestUpdate request) {
        contractDataRepository.deleteById(request.getId());
    }

    public Set<HibernateContract_item> findSetHibernateContract_itemsByCustomerAuthenticateAndRelevance() {
        HibernateContract hibernateContract = new HibernateContract();
        for (HibernateContract contract : customerDataService.findHibernateCustomerByAuthenticationInfo().getContracts()) {
            if (contract.getRelevance().equals(ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus())) {
                hibernateContract = contract;
            } else throw new EntityNotFoundException("Актуальных контрактов нет");
        }
        return hibernateContract.getContracts_items();
    }

    public List<HibernateItem> findSetItemsByCustomerAuthenticateAndRelevance() {
        HibernateContract hibernateContract = new HibernateContract();
        for (HibernateContract contract : customerDataService.findHibernateCustomerByAuthenticationInfo().getContracts()) {
            if (contract.getRelevance().equals(ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus())) {
                hibernateContract = contract;
            } else throw new EntityNotFoundException("Актуальных контрактов нет");
        }
        return hibernateContract.getItems();
    }

    public Set<HibernateContract_item> findSetHibernateContracts_items(Integer id) {
        HibernateContract hibernateContract = findHibernateContractById(id);
        return hibernateContract.getContracts_items();
    }
}
