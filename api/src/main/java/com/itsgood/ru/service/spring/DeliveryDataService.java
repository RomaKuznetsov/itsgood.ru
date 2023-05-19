package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestCreate;
import com.itsgood.ru.controller.request.delivery.DeliveryRequestSearch;
import com.itsgood.ru.controller.request.delivery.DeliveryRequestUpdate;
import com.itsgood.ru.converters.DeliveryConverterRequestCreate;
import com.itsgood.ru.converters.DeliveryConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
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

    public DeliveryDTO findHibernateDeliveryById(Integer id) {
        Optional<DeliveryDTO> searchResult = deliveryDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<DeliveryDTO> findAll() {
        return deliveryDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public DeliveryDTO createHibernateDelivery(DeliveryRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        DeliveryDTO deliveryDTO = deliveryConverterRequestCreate.convert(request);
        if (customerDTO.getAddress().contains(deliveryDTO.getAddress())) {
            deliveryDTO = deliveryDataRepository.save(deliveryDTO);
        } else throw new EntityNotFoundException();
        return deliveryDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public DeliveryDTO updateHibernateDelivery(DeliveryRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        DeliveryDTO deliveryDTO = deliveryConverterRequestUpdate.convert(request);
        if (customerDTO.getAddress().contains(deliveryDTO.getAddress())) {
            deliveryDataRepository.save(deliveryDTO);
        } else throw new EntityNotFoundException();
        return deliveryDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDelivery(DeliveryRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        DeliveryDTO deliveryDTO = deliveryConverterRequestUpdate.convert(request);
        if (customerDTO.getAddress().contains(addressDataService.
                findHibernateAddressById(request.getAddress_id()))) {
            deliveryDataRepository.delete(deliveryDTO);
        } else throw new EntityNotFoundException();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateDeliveryById(DeliveryRequestSearch request) {
        deliveryDataRepository.deleteById(request.getId());
    }

}
