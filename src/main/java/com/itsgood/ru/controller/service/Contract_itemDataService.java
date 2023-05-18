package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.Contract_itemConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.Contract_itemConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestCreate;
import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestSearch;
import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestUpdate;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestCreate;
import com.itsgood.ru.controller.springDataRepository.Contract_itemDataRepository;
import com.itsgood.ru.enums.ContractRelevance;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class Contract_itemDataService {

    private final ContractDataService contractDataService;
    private final CustomerDataService customerDataService;
    private final Contract_itemDataRepository contract_itemDataRepository;
    private final DeliveryDataService deliveryDataService;
    private final Contract_itemConverterRequestCreate contract_itemConverterRequestCreate;
    private final Contract_itemConverterRequestUpdate contract_itemConverterRequestUpdate;

    public HibernateContract_item findHibernateContract_itemById(Integer id) {
        Optional<HibernateContract_item> searchResult = contract_itemDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateContract_item createHibernateContract_item(Contract_itemRequestCreate request) {
        HibernateContract_item hibernateContract_item = contract_itemConverterRequestCreate.convert(request);
        HibernateContract hibernateContract = contractDataService.findHibernateContractByAuthenticateAndRelevance();
        if (hibernateContract.getId() != 0) {
            hibernateContract_item.setContract(hibernateContract);
        } else hibernateContract = contractDataService.createHibernateContract(new ContractRequestCreate());
        hibernateContract_item.setContract(hibernateContract);
        return hibernateContract_item;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateContract_item updateHibernateContract_item(Contract_itemRequestUpdate request) {
        HibernateDelivery hibernateDelivery = new HibernateDelivery();
        HibernateContract_item hibernateContract_item = contract_itemConverterRequestUpdate.convert(request);
        if (hibernateContract_item.getDelivery().getId() != 0) {
            contract_itemDataRepository.save(hibernateContract_item);
        } else hibernateDelivery = deliveryDataService.createHibernateDelivery(new DeliveryRequestCreate());
        hibernateContract_item.setDelivery(hibernateDelivery);
        return hibernateContract_item;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContract_itemById(Integer id) {
        contract_itemDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateContract_item(Contract_itemRequestUpdate request) {
        HibernateContract_item hibernateContract_item = contract_itemConverterRequestUpdate.convert(request);
        if (contractDataService.findSetHibernateContract_item(request.getContract_id()).
                contains(hibernateContract_item)) {
            contract_itemDataRepository.delete(hibernateContract_item);
        }
    }
}
