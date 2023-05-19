package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestCreate;
import com.itsgood.ru.controller.request.delivery.DeliveryRequestSearch;
import com.itsgood.ru.controller.request.delivery.DeliveryRequestUpdate;
import com.itsgood.ru.converters.DeliveryConverterRequestCreate;
import com.itsgood.ru.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import com.itsgood.ru.repository.spring.DeliveryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        HibernateDelivery hibernateDelivery = deliveryConverterRequestCreate.convert(request);
        if (hibernateCustomer.getAddress().contains(hibernateDelivery.getAddress())) {
            hibernateDelivery = deliveryDataRepository.save(hibernateDelivery);
        } else throw new EntityNotFoundException();
        return hibernateDelivery;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateDelivery updateHibernateDelivery(DeliveryRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateDelivery hibernateDelivery = deliveryConverterRequestUpdate.convert(request);
        if (hibernateCustomer.getAddress().contains(hibernateDelivery.getAddress())) {
            deliveryDataRepository.save(hibernateDelivery);
        } else throw new EntityNotFoundException();
        return hibernateDelivery;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDelivery(DeliveryRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateDelivery hibernateDelivery = deliveryConverterRequestUpdate.convert(request);
        if (hibernateCustomer.getAddress().contains(addressDataService.
                findHibernateAddressById(request.getAddress_id()))) {
            deliveryDataRepository.delete(hibernateDelivery);
        } else throw new EntityNotFoundException();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDeliveryById(DeliveryRequestSearch request) {
        deliveryDataRepository.deleteById(request.getId());
    }

}
