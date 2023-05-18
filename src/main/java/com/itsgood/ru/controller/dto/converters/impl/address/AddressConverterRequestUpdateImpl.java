package com.itsgood.ru.controller.dto.converters.impl.address;

import com.itsgood.ru.controller.dto.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.AddressDataRepository;
import com.itsgood.ru.controller.springDataRepository.CustomerDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
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
