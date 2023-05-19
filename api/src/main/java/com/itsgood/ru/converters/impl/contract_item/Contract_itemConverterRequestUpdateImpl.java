package com.itsgood.ru.converters.impl.contract_item;

import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestUpdate;
import com.itsgood.ru.converters.Contract_itemConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.service.spring.ContractDataService;
import com.itsgood.ru.service.spring.DeliveryDataService;
import com.itsgood.ru.service.spring.ItemDataService;
import com.itsgood.ru.repository.spring.Contract_itemDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Contract_itemConverterRequestUpdateImpl implements Contract_itemConverterRequestUpdate {
    private final ItemDataService itemDataService;
    private final ContractDataService contractDataService;
    private final DeliveryDataService deliveryDataService;
    private final Contract_itemDataRepository contract_itemDataRepository;
    @Override
    public Contract_itemDTO convert(Contract_itemRequestUpdate request) {
        Optional<Contract_itemDTO> searchResult = contract_itemDataRepository.findById(request.getId());
        Contract_itemDTO contract_itemDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        contract_itemDTO.setContract(contractDataService.findHibernateContractByAuthenticateAndRelevance());
        contract_itemDTO.setItem(itemDataService.findHibernateItemById(request.getItem_id()));
        contract_itemDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        contract_itemDTO.setDelivery(deliveryDataService.findHibernateDeliveryById(request.getDelivery_id()));
        return contract_itemDTO;
    }
}
