package com.itsgood.ru.converters.impl.item;

import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.converters.ItemConverterRequestUpdate;
import com.itsgood.ru.domain.ItemDTO;
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
    public ItemDTO convert(ItemRequestUpdate request) {
        Optional<ItemDTO> searchResult = itemDataRepository.findById(request.getId());
        ItemDTO itemDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        itemDTO.setTitle(request.getTitle());
        itemDTO.setPrice(request.getPrice());
        itemDTO.setFirm(request.getFirm());
        itemDTO.setLink(request.getLink());
        itemDTO.setDescription(request.getDescription());
        itemDTO.setWeight(request.getWeight());
        itemDTO.setVolume(request.getVolume());
        itemDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return itemDTO;
    }

}
