package com.itsgood.ru.converters.impl.delivery;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestUpdate;
import com.itsgood.ru.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.repository.spring.DeliveryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeliveryConverterRequestUpdateImpl implements DeliveryConverterRequestUpdate {
    private final AddressDataService addressDataService;
    private final DeliveryDataRepository deliveryDataRepository;

    @Override
    public HibernateDelivery convert(DeliveryRequestUpdate request) {
        Optional<HibernateDelivery> searchResult = deliveryDataRepository.findById(request.getId());
        HibernateDelivery hibernateDelivery = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateDelivery.setFirstname(request.getFirstname());
        hibernateDelivery.setLastname(request.getLastname());
        hibernateDelivery.setPhone(request.getPhone());
        hibernateDelivery.setShipment_time(request.getShipment_time());
        hibernateDelivery.setStock_index(request.getStock_index());
        hibernateDelivery.setDistance(request.getDistance());
        hibernateDelivery.setPrice(request.getPrice());
        hibernateDelivery.setValidity(request.getValidity());
        hibernateDelivery.setAddress(addressDataService.findHibernateAddressById(request.getAddress_id()));
        return hibernateDelivery;

    }
}

