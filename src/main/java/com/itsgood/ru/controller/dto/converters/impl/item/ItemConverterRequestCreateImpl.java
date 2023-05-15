//package com.itsgood.ru.controller.dto.converters.impl.item;
//
//import com.itsgood.ru.controller.dto.converters.ItemConverterRequestCreate;
//import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestCreate;
//import com.itsgood.ru.controller.service.CategoryDataService;
//import com.itsgood.ru.hibernate.domain.HibernateCategory;
//import com.itsgood.ru.hibernate.domain.HibernateItem;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.sql.Timestamp;
//
//@Component
//@RequiredArgsConstructor
//public class ItemConverterRequestCreateImpl implements ItemConverterRequestCreate {
//
//
//    @Override
//    public HibernateItem convert(ItemRequestCreate request) {
//        HibernateItem hibernateItem = new HibernateItem();
//        hibernateItem.setTitle(request.getTitle());
//        hibernateItem.setPrice(request.getPrice());
//        hibernateItem.setFirm(request.getFirm());
//        hibernateItem.setLink(request.getLink());
//        hibernateItem.setDescription(request.getDescription());
//        hibernateItem.setWeight(request.getWeight());
//        hibernateItem.setVolume(request.getVolume());
//        hibernateItem.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
//        return hibernateItem;
//    }
//}
