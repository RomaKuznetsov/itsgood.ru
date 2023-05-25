package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.converters.ContractConverterRequestCreate;
import com.itsgood.ru.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.repository.spring.ContractDataRepository;
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
public class ContractDataService {
    private final CustomerDataService customerDataService;
    private final ContractDataRepository contractDataRepository;
    private final ContractConverterRequestCreate contractConverterRequestCreate;
    private final ContractConverterRequestUpdate contractConverterRequestUpdate;

    public List<ContractDTO> findAllContract() {
        return contractDataRepository.findAll();
    }

    public ContractDTO findContractById(Integer id) {
        Optional<ContractDTO> searchResult = contractDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public ContractDTO findContractByAuthenticate() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchContract = contractDataRepository.findContractByCustomer(customerDTO);
        return searchContract.orElseThrow(EntityNotFoundException::new);
    }

    //ok
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ContractDTO createContract(ContractRequestCreate request) {
        ContractDTO contractDTO = contractConverterRequestCreate.convert(request);
        contractDTO = contractDataRepository.save(contractDTO);
        return contractDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public ContractDTO updateContract(ContractRequestUpdate request) {
        ContractDTO contractDTO = contractConverterRequestUpdate.convert(request);
        return contractDataRepository.save(contractDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteContractByAuthenticate(ContractRequestUpdate request) {
        ContractDTO contractDTO = contractConverterRequestUpdate.convert(request);
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        ContractDTO searchContract = customerDTO.getContract();
        if (!searchContract.equals(contractDTO)) {
            contractDataRepository.delete(contractDTO);
        } else throw new EntityNotFoundException("No such contract");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteContractById(ContractRequestUpdate request) {
        contractDataRepository.deleteById(request.getId());
    }


    public Set<BucketDTO> findSetEquipmentsByAuthenticate() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchResult = contractDataRepository.findContractByCustomer(customerDTO);
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getBuckets();
    }

    public List<ItemDTO> findSetItemsByCustomerAuthenticate() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchResult = contractDataRepository.findContractByCustomer(customerDTO);
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getItems();
    }

    @Cacheable("bucket")
    public Set<BucketDTO> findSetBucket() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<ContractDTO> searchContract = contractDataRepository.findContractByCustomer(customerDTO);
        ContractDTO contractDTO = searchContract.orElseThrow(EntityNotFoundException::new);
        return contractDTO.getBuckets();
    }

}
