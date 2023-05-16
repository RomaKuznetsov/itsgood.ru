package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.DeliveryConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestCreate;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestSearch;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.DeliveryDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class DeliveryDataService {

    private final AddressDataService addressDataService;
    private final CustomerDataService customerDataService;
    private final DeliveryDataRepository deliveryDataRepository;
    private final DeliveryConverterRequestUpdate deliveryConverterRequestUpdate;
    private final DeliveryConverterRequestCreate deliveryConverterRequestCreate;

    public HibernateDelivery findHibernateDeliveryById(Integer id) {
        Optional<HibernateDelivery> searchResult = deliveryDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<HibernateDelivery> findAll() {
        return deliveryDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateDelivery createHibernateDelivery(DeliveryRequestCreate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateDelivery hibernateDelivery = new HibernateDelivery();
        if (hibernateCustomer.getAddress().contains(addressDataService.
                findHibernateAddressById(request.getAddress_id()))) {
            hibernateDelivery = deliveryDataRepository.save(deliveryConverterRequestCreate.convert(request));
        } else throw new EntityNotFoundException("Ошибка адресата");
        return hibernateDelivery;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateDelivery updateHibernateDelivery(DeliveryRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateDelivery hibernateDelivery = new HibernateDelivery();
        if (hibernateCustomer.getAddress().contains(addressDataService.findHibernateAddressById(request.getAddress_id()))) {
            deliveryDataRepository.save(deliveryConverterRequestUpdate.convert(request));
        } else throw new EntityNotFoundException("Ошибка адресата");
        return hibernateDelivery;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDelivery(DeliveryRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        if (hibernateCustomer.getAddress().contains(addressDataService.
                findHibernateAddressById(request.getAddress_id()))) {
            deliveryDataRepository.delete(deliveryConverterRequestUpdate.convert(request));
        } else throw new EntityNotFoundException("Ошибка адресата");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDeliveryById(DeliveryRequestUpdate request) {
        deliveryDataRepository.deleteById(request.getId());
    }

}
