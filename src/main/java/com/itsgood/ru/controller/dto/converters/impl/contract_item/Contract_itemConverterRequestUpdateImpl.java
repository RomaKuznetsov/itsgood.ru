package com.itsgood.ru.controller.dto.converters.impl.contract_item;

import com.itsgood.ru.controller.dto.converters.Contract_itemConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestUpdate;
import com.itsgood.ru.controller.service.ContractDataService;
import com.itsgood.ru.controller.service.DeliveryDataService;
import com.itsgood.ru.controller.service.ItemDataService;
import com.itsgood.ru.controller.springDataRepository.Contract_itemDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
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
    public HibernateContract_item convert(Contract_itemRequestUpdate request) {
        Optional<HibernateContract_item> searchResult = contract_itemDataRepository.findById(request.getId());
        HibernateContract_item hibernateContract_item = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateContract_item.setContract(contractDataService.findHibernateContractByAuthenticateAndRelevance());
        hibernateContract_item.setItem(itemDataService.findHibernateItemById(request.getItem_id()));
        hibernateContract_item.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateContract_item.setDelivery(deliveryDataService.findHibernateDeliveryById(request.getDelivery_id()));
        return hibernateContract_item;
    }
}
