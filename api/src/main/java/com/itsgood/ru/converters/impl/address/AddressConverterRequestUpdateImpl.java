package com.itsgood.ru.converters.impl.address;

import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.repository.spring.AddressDataRepository;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.domain.hibernate.AddressDTO;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
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
    public AddressDTO convert(AddressRequestUpdate request) {
        Optional<AddressDTO> searchAddress = addressDataRepository.findById(request.getId());
        AddressDTO addressDTO = searchAddress.orElseThrow(EntityNotFoundException::new);
        Optional<CustomerDTO> searchCustomer = customerDataRepository.findById(request.getId());
        addressDTO.setCustomer(searchCustomer.orElseThrow(EntityNotFoundException::new));
        addressDTO.setCode(request.getCode());
        addressDTO.setCountry(request.getCountry());
        addressDTO.setRegion(request.getRegion());
        addressDTO.setCity(request.getCity());
        addressDTO.setStreet(request.getStreet());
        addressDTO.setHouse(request.getHouse());
        addressDTO.setFrame(request.getFrame());
        addressDTO.setApartment(request.getApartment());
        return addressDTO;
    }
}
