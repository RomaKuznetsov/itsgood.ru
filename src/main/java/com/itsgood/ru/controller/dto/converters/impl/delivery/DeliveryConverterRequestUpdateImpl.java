package com.itsgood.ru.controller.dto.converters.impl.delivery;

import com.itsgood.ru.controller.dto.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestUpdate;
import com.itsgood.ru.controller.service.AddressDataService;
import com.itsgood.ru.controller.service.DeliveryDataService;
import com.itsgood.ru.controller.springDataRepository.DeliveryDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.utilits.RandomValuesGenerator;
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

