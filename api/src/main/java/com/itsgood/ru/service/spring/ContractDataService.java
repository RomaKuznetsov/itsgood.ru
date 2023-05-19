package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.converters.ContractConverterRequestCreate;
import com.itsgood.ru.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.repository.spring.ContractDataRepository;
import com.itsgood.ru.codes.ContractRelevance;
import lombok.RequiredArgsConstructor;
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

    public List<ContractDTO> findAllHibernateContract() {
        return contractDataRepository.findAll();
    }

    public ContractDTO findHibernateContractById(Integer id) {
        Optional<ContractDTO> searchResult = contractDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public ContractDTO findHibernateContractByAuthenticateAndRelevance() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(customerDTO,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<ContractDTO> findAllHibernateContractsByAuthenticateAndIrrelevance() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<ContractDTO>> searchResult = contractDataRepository.
                findAllHibernateContractsByCustomerAndRelevance(customerDTO,
                        ContractRelevance.RELEVANCE_CONTRACT_IRRELEVANCE.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ContractDTO createHibernateContract(ContractRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        ContractDTO contractDTO = contractConverterRequestCreate.convert(request);
        Set<ContractDTO> searchResult = customerDTO.getContracts();
        if (!searchResult.contains(contractDTO)) {
            contractDTO.setCustomer(customerDTO);
            contractDTO = contractDataRepository.save(contractDTO);
        } else throw new EntityExistsException();
        return contractDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ContractDTO updateHibernateContract(ContractRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Set<ContractDTO> searchRequest = customerDTO.getContracts();
        ContractDTO contractDTO = contractConverterRequestUpdate.convert(request);
        if (!searchRequest.contains(findHibernateContractById(request.getId()))) {
            List<ItemDTO> items = contractDTO.getItems();
            contractDTO.setSum_order(Math.toIntExact(items.stream().map(item -> item.getPrice()).count()));
            contractDTO.setCustomer(customerDTO);
            contractDataRepository.save(contractDTO);
        }
        return contractDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContractByAuthenticate(ContractRequestUpdate request) {
        contractDataRepository.delete(contractConverterRequestUpdate.convert(request));
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContractById(ContractRequestUpdate request) {
        contractDataRepository.deleteById(request.getId());
    }


    public Set<Contract_itemDTO> findSetHibernateContract_itemsByAuthenticateAndRelevance() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(customerDTO,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getContracts_items();
    }

    public List<ItemDTO> findSetItemsByCustomerAuthenticateAndRelevance() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchResult = contractDataRepository.
                findHibernateContractByCustomerAndRelevance(customerDTO,
                        ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getItems();
    }

    //    @Cacheable("contract_item")
    public Set<Contract_itemDTO> findSetHibernateContract_item(Integer id) {
        Optional<ContractDTO> searchResult = contractDataRepository.findById(id);
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getContracts_items();
    }

}
