package com.itsgood.ru.controller.dto.converters.impl.contract_item;

import com.itsgood.ru.controller.dto.converters.Contract_itemConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestCreate;
import com.itsgood.ru.controller.springDataRepository.ItemDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateItem;
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
    public HibernateContract_item convert(Contract_itemRequestCreate request) {
        HibernateContract_item hibernateContract_item = new HibernateContract_item();
        Optional<HibernateItem> searchItem = itemDataRepository.findById(request.getItem_id());
        hibernateContract_item.setItem(searchItem.orElseThrow(EntityNotFoundException::new));
        hibernateContract_item.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return hibernateContract_item;
    }
}
