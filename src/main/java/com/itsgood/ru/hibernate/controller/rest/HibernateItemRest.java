package com.itsgood.ru.hibernate.controller.rest;

import com.itsgood.ru.domain.Item;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import com.itsgood.ru.hibernate.service.HibernateItemService;
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
        List<HibernateItem> listAllItem;
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

           HibernateItem hibernateItem = HibernateItem.builder().title(request.getTitle()).
                    price(request.getPrice()).
                    firm(request.getFirm()).
                    weight(request.getWeight()).
                    volume(request.getVolume()).
                    create_time(new Timestamp(System.currentTimeMillis())).build();

//                   setCategory_id(request.getCategory_id());
            itemService.create(hibernateItem);

            hibernateItem = itemService.create(hibernateItem);
            return new ResponseEntity<>(hibernateItem, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
