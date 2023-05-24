package com.itsgood.ru.converters.impl.address;

import com.itsgood.ru.controller.request.address.AddressRequestCreate;
import com.itsgood.ru.converters.AddressConverterRequestCreate;
import com.itsgood.ru.domain.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressConverterRequestCreateImpl implements AddressConverterRequestCreate {

    @Override
    public AddressDTO convert(AddressRequestCreate request) {
      AddressDTO addressDTO = new AddressDTO();
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
