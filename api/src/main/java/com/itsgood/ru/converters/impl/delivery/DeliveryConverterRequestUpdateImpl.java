package com.itsgood.ru.converters.impl.delivery;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestUpdate;
import com.itsgood.ru.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
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
    public DeliveryDTO convert(DeliveryRequestUpdate request) {
        Optional<DeliveryDTO> searchResult = deliveryDataRepository.findById(request.getId());
        DeliveryDTO deliveryDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        deliveryDTO.setFirstname(request.getFirstname());
        deliveryDTO.setLastname(request.getLastname());
        deliveryDTO.setPhone(request.getPhone());
        deliveryDTO.setShipment_time(request.getShipment_time());
        deliveryDTO.setStock_index(request.getStock_index());
        deliveryDTO.setDistance(request.getDistance());
        deliveryDTO.setPrice(request.getPrice());
        deliveryDTO.setValidity(request.getValidity());
        deliveryDTO.setAddress(addressDataService.findHibernateAddressById(request.getAddress_id()));
        return deliveryDTO;

    }
}

