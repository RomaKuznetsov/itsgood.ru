package com.itsgood.ru.controller.dto.converters.impl.address;

import com.itsgood.ru.controller.dto.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.AddressDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressConverterRequestUpdateImpl implements AddressConverterRequestUpdate {
    private final AddressDataRepository addressDataRepository;
    @Override
    public HibernateAddress convert(AddressRequestUpdate request) {
        Optional<HibernateAddress> searchResult = addressDataRepository.findById(request.getId());
        HibernateAddress hibernateAddress = searchResult.orElseThrow(EntityNotFoundException::new);
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
