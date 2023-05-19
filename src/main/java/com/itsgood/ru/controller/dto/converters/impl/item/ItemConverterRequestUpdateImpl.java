package com.itsgood.ru.controller.dto.converters.impl.item;

import com.itsgood.ru.controller.dto.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
public class ItemConverterRequestUpdateImpl implements ItemConverterRequestUpdate {

    @Override
    public HibernateItem convert(ItemRequestUpdate request) {
        HibernateItem hibernateItem = new HibernateItem();
        hibernateItem.setId(request.getId());
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
