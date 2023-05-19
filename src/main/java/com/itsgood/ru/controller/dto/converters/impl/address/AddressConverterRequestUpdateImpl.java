package com.itsgood.ru.controller.dto.converters.impl.address;

import com.itsgood.ru.controller.dto.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressConverterRequestUpdateImpl implements AddressConverterRequestUpdate {


    @Override
    public HibernateAddress convert(AddressRequestUpdate request) {
        HibernateAddress hibernateAddress = new HibernateAddress();
        hibernateAddress.setId(request.getId());
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
