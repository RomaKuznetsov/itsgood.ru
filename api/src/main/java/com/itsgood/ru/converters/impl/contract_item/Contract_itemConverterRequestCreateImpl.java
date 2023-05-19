package com.itsgood.ru.converters.impl.contract_item;

import com.itsgood.ru.controller.request.contract_item.Contract_itemRequestCreate;
import com.itsgood.ru.converters.Contract_itemConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.repository.spring.ItemDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Contract_itemConverterRequestCreateImpl implements Contract_itemConverterRequestCreate {
    private final ItemDataRepository itemDataRepository;
    @Override
    public Contract_itemDTO convert(Contract_itemRequestCreate request) {
        Contract_itemDTO contract_itemDTO = new Contract_itemDTO();
        Optional<ItemDTO> searchItem = itemDataRepository.findById(request.getItem_id());
        contract_itemDTO.setItem(searchItem.orElseThrow(EntityNotFoundException::new));
        contract_itemDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return contract_itemDTO;
    }
}
