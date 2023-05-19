package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestCreate;
import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestUpdate;
import com.itsgood.ru.controller.request.delivery.DeliveryRequestCreate;
import com.itsgood.ru.converters.Contract_itemConverterRequestCreate;
import com.itsgood.ru.converters.Contract_itemConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
import com.itsgood.ru.repository.spring.Contract_itemDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class Contract_itemDataService {

    private final ContractDataService contractDataService;
    private final Contract_itemDataRepository contract_itemDataRepository;
    private final DeliveryDataService deliveryDataService;
    private final Contract_itemConverterRequestCreate contract_itemConverterRequestCreate;
    private final Contract_itemConverterRequestUpdate contract_itemConverterRequestUpdate;

    public Contract_itemDTO findHibernateContract_itemById(Integer id) {
        Optional<Contract_itemDTO> searchResult = contract_itemDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Contract_itemDTO createHibernateContract_item(Contract_itemRequestCreate request) {
        Contract_itemDTO contract_itemDTO = contract_itemConverterRequestCreate.convert(request);
        ContractDTO contractDTO = contractDataService.findHibernateContractByAuthenticateAndRelevance();
        if (contractDTO.getId() != 0) {
            contract_itemDTO.setContract(contractDTO);
        } else contractDTO = contractDataService.createHibernateContract(new ContractRequestCreate());
        contract_itemDTO.setContract(contractDTO);
        return contract_itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Contract_itemDTO updateHibernateContract_item(Contract_itemRequestUpdate request) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        Contract_itemDTO contract_itemDTO = contract_itemConverterRequestUpdate.convert(request);
        if (contract_itemDTO.getDelivery().getId() != 0) {
            contract_itemDataRepository.save(contract_itemDTO);
        } else deliveryDTO = deliveryDataService.createHibernateDelivery(new DeliveryRequestCreate());
        contract_itemDTO.setDelivery(deliveryDTO);
        return contract_itemDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContract_itemById(Integer id) {
        contract_itemDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContract_item(Contract_itemRequestUpdate request) {
        Contract_itemDTO contract_itemDTO = contract_itemConverterRequestUpdate.convert(request);
        if (contractDataService.findSetHibernateContract_item(request.getContract_id()).
                contains(contract_itemDTO)) {
            contract_itemDataRepository.delete(contract_itemDTO);
        }
    }
}
