package com.itsgood.ru.converters.impl.contract_item;

import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestUpdate;
import com.itsgood.ru.converters.Contract_itemConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateContract_item;
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
