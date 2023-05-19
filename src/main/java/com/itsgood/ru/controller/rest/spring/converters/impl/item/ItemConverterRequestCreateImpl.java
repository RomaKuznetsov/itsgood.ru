package com.itsgood.ru.controller.rest.spring.converters.impl.item;

import com.itsgood.ru.controller.rest.spring.converters.ItemConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ItemConverterRequestCreateImpl implements ItemConverterRequestCreate {


    @Override
    public HibernateItem convert(ItemRequestCreate request) {
        HibernateItem hibernateItem = new HibernateItem();
        hibernateItem.setTitle(request.getTitle());
        hibernateItem.setPrice(request.getPrice());
        hibernateItem.setFirm(request.getFirm());
        hibernateItem.setLink(request.getLink());
        hibernateItem.setDescription(request.getDescription());
        hibernateItem.setWeight(request.getWeight());
        hibernateItem.setVolume(request.getVolume());
        hibernateItem.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return hibernateItem;
    }
}
