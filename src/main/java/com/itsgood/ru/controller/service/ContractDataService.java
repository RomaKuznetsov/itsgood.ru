package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.ContractConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.ContractDataRepository;
import com.itsgood.ru.enums.ContractRelevance;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateItem;
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
public class ContractDataService {
    private final CustomerDataService customerDataService;
    private final ContractDataRepository contractDataRepository;
    private final ContractConverterRequestCreate contractConverterRequestCreate;
    private final ContractConverterRequestUpdate contractConverterRequestUpdate;

    public List<HibernateContract> findAllHibernateContract() {
        return contractDataRepository.findAll();
    }

    public HibernateContract findHibernateContractById(Integer id) {
        Optional<HibernateContract> searchResult = contractDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateContract findHibernateContractByAuthenticateAndRelevance() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<HibernateContract> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(hibernateCustomer,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<HibernateContract> findAllHibernateContractsByAuthenticateAndIrrelevance() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernateContract>> searchResult = contractDataRepository.
                findAllHibernateContractsByCustomerAndRelevance(hibernateCustomer,
                        ContractRelevance.RELEVANCE_CONTRACT_IRRELEVANCE.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateContract createHibernateContract(ContractRequestCreate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateContract hibernateContract = contractConverterRequestCreate.convert(request);
        Set<HibernateContract> searchResult = hibernateCustomer.getContracts();
        if (!searchResult.contains(hibernateContract)) {
            hibernateContract.setCustomer(hibernateCustomer);
            hibernateContract = contractDataRepository.save(hibernateContract);
        } else throw new EntityExistsException();
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
        contractDataRepository.delete(contractConverterRequestUpdate.convert(request));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContractById(ContractRequestUpdate request) {
        contractDataRepository.deleteById(request.getId());
    }


    public Set<HibernateContract_item> findSetHibernateContract_itemsByAuthenticateAndRelevance() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<HibernateContract> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(hibernateCustomer,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        HibernateContract hibernateContract = searchResult.orElseThrow(EntityNotFoundException::new);
        return hibernateContract.getContracts_items();
    }

    public List<HibernateItem> findSetItemsByCustomerAuthenticateAndRelevance() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<HibernateContract> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(hibernateCustomer,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        HibernateContract hibernateContract = searchResult.orElseThrow(EntityNotFoundException::new);
        return hibernateContract.getItems();
    }

    //    @Cacheable("contract_item")
    public Set<HibernateContract_item> findSetHibernateContract_item(Integer id) {
        Optional<HibernateContract> searchResult = contractDataRepository.findById(id);
        HibernateContract hibernateContract = searchResult.orElseThrow(EntityNotFoundException::new);
        return hibernateContract.getContracts_items();
    }

}
