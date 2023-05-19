package com.itsgood.ru.converters.impl.item;

import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateItem;
import com.itsgood.ru.repository.spring.ItemDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemConverterRequestUpdateImpl implements ItemConverterRequestUpdate {

    private final ItemDataRepository itemDataRepository;

    @Override
    public HibernateItem convert(ItemRequestUpdate request) {
        Optional<HibernateItem> searchResult = itemDataRepository.findById(request.getId());
        HibernateItem hibernateItem = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateItem.setTitle(request.getTitle());
        hibernateItem.setPrice(request.getPrice());
        hibernateItem.setFirm(request.getFirm());
        hibernateItem.setLink(request.getLink());
        hibernateItem.setDescription(request.getDescription());
        hibernateItem.setWeight(request.getWeight());
        hibernateItem.setVolume(request.getVolume());
        hibernateItem.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return hibernateItem;
    }

}
