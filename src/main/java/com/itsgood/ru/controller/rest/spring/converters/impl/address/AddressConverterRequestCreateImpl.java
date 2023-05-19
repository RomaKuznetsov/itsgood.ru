package com.itsgood.ru.controller.rest.spring.converters.impl.address;

import com.itsgood.ru.controller.rest.spring.converters.AddressConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressConverterRequestCreateImpl implements AddressConverterRequestCreate {

    @Override
    public HibernateAddress convert(AddressRequestCreate request) {
      HibernateAddress hibernateAddress = new HibernateAddress();
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
