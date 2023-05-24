package com.itsgood.ru.old.rest.hibernate;

import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.old.domain.Item;
import com.itsgood.ru.old.service.hibernate.HibernateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/hibernate/item")
public class HibernateItemRest {

    private final HibernateItemService itemService;

    @GetMapping(value = "/findAllItems", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllItems() {
        List<ItemDTO> listAllItem;
        try {
            listAllItem = itemService.findAll();
            return new ResponseEntity<>(listAllItem, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping(value = "/create", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> createItem(@RequestBody Item request) {
        try {
            //Spring converter: request -> entity
            //HibernateItem hibernateItem = converterService.convert(request.HibernateItem.class)

           ItemDTO itemDTO = ItemDTO.builder().title(request.getTitle()).
                    price(request.getPrice()).
                    firm(request.getFirm()).
                    weight(request.getWeight()).
                    volume(request.getVolume()).
                    create_time(new Timestamp(System.currentTimeMillis())).build();

//                   setCategory_id(request.getCategory_id());
            itemService.create(itemDTO);

            itemDTO = itemService.create(itemDTO);
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
