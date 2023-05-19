package com.itsgood.ru.converters.impl.address;

import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.repository.spring.AddressDataRepository;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.domain.hibernate.HibernateAddress;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressConverterRequestUpdateImpl implements AddressConverterRequestUpdate {
    private final AddressDataRepository addressDataRepository;

    private final CustomerDataRepository customerDataRepository;
    @Override
    public HibernateAddress convert(AddressRequestUpdate request) {
        Optional<HibernateAddress> searchAddress = addressDataRepository.findById(request.getId());
        HibernateAddress hibernateAddress = searchAddress.orElseThrow(EntityNotFoundException::new);
        Optional<HibernateCustomer> searchCustomer = customerDataRepository.findById(request.getId());
        hibernateAddress.setCustomer(searchCustomer.orElseThrow(EntityNotFoundException::new));
        hibernateAddress.setCode(request.getCode());
        hibernateAddress.setCountry(request.getCountry());
        hibernateAddress.setRegion(request.getRegion());
        hibernateAddress.setCity(request.getCity());
        hibernateAddress.setStreet(request.getStreet());
        hibernateAddress.setHouse(request.getHouse());
        hibernateAddress.setFrame(request.getFrame());
        hibernateAddress.setApartment(request.getApartment());
        return hibernateAddress;
    }
}
