//package com.itsgood.ru.controller.dto.converters.impl.contract_item;
//
//import com.itsgood.ru.controller.dto.converters.Contract_itemConverterRequestCreate;
//import com.itsgood.ru.controller.dto.request.contract_itemDTO.Contract_itemRequestCreate;
//import com.itsgood.ru.hibernate.domain.HibernateContract_item;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.sql.Timestamp;
//
//@Component
//@RequiredArgsConstructor
//public class Contract_itemConverterRequestCreateImpl implements Contract_itemConverterRequestCreate {
//
//    @Override
//    public HibernateContract_item convert(Contract_itemRequestCreate request) {
//        HibernateContract_item hibernateContract_item = new HibernateContract_item();
//        hibernateContract_item.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
//        return hibernateContract_item;
//    }
//}
