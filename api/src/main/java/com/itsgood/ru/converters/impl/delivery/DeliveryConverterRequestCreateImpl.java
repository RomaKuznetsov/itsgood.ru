package com.itsgood.ru.converters.impl.delivery;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestCreate;
import com.itsgood.ru.converters.DeliveryConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.utilits.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class DeliveryConverterRequestCreateImpl implements DeliveryConverterRequestCreate {

    private final RandomValuesGenerator randomValuesGenerator;
    private final AddressDataService addressDataService;

    @Override
    public HibernateDelivery convert(DeliveryRequestCreate request) {
        HibernateDelivery hibernateDelivery = new HibernateDelivery();
        hibernateDelivery.setFirstname(request.getFirstname());
        hibernateDelivery.setLastname(request.getLastname());
        hibernateDelivery.setPhone(request.getPhone());
        hibernateDelivery.setLoading_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime().plusHours(12)));
        hibernateDelivery.setStock_index(Math.toIntExact(Long.valueOf(randomValuesGenerator.generateRandomString())));
        hibernateDelivery.setDistance(request.getDistance());
        hibernateDelivery.setValidity(Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().plusDays(3)));
        hibernateDelivery.setAddress(addressDataService.findHibernateAddressById(request.getAddress_id()));
        //нужна валидация и forward на страницу создания адреса доставки
        return hibernateDelivery;
    }
}
