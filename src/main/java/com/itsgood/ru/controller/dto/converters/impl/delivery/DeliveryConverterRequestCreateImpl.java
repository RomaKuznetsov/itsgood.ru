//package com.itsgood.ru.controller.dto.converters.impl.delivery;
//
//import com.itsgood.ru.controller.dto.converters.DeliveryConverterRequestCreate;
//import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestCreate;
//import com.itsgood.ru.controller.service.AddressDataService;
//import com.itsgood.ru.hibernate.domain.HibernateDelivery;
//import com.itsgood.ru.utilits.RandomValuesGenerator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.convert.threetenbp.ThreeTenBackPortJpaConverters;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class DeliveryConverterRequestCreateImpl implements DeliveryConverterRequestCreate {
//
//    private final RandomValuesGenerator randomValuesGenerator;
//    private final AddressDataService addressDataService;
//
//    @Override
//    public HibernateDelivery convert(DeliveryRequestCreate request) {
//        HibernateDelivery hibernateDelivery = new HibernateDelivery();
//        hibernateDelivery.setFirstname(request.getFirstname());
//        hibernateDelivery.setLastname(request.getLastname());
//        hibernateDelivery.setPhone(request.getPhone());
//        hibernateDelivery.setLoading_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime().plusHours(12)));
//        hibernateDelivery.setStock_index(Math.toIntExact(Long.valueOf(randomValuesGenerator.generateRandomString())));
//        hibernateDelivery.setDistance(request.getDistance());
//        hibernateDelivery.setValidity(Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().plusDays(3)));
//        hibernateDelivery.setAddress(addressDataService.findHibernateAddressById(request.getAddress_id()));
//        return hibernateDelivery;
//    }
//}
