package com.itsgood.ru.converters.impl.item;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.converters.ItemConverterRequestCreate;
import com.itsgood.ru.domain.ItemDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ItemConverterRequestCreateImpl implements ItemConverterRequestCreate {


    @Override
    public ItemDTO convert(ItemRequestCreate request) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setTitle(request.getTitle());
        itemDTO.setPrice(request.getPrice());
        itemDTO.setFirm(request.getFirm());
        itemDTO.setLink(request.getLink());
        itemDTO.setDescription(request.getDescription());
        itemDTO.setWeight(request.getWeight());
        itemDTO.setVolume(request.getVolume());
        itemDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return itemDTO;
    }
}
