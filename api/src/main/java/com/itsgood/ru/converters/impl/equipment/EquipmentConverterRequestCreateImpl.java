package com.itsgood.ru.converters.impl.equipment;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestCreate;
import com.itsgood.ru.converters.EquipmentConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.EquipmentDTO;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.utilits.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class EquipmentConverterRequestCreateImpl implements EquipmentConverterRequestCreate {

    private final RandomValuesGenerator randomValuesGenerator;
    private final AddressDataService addressDataService;

    @Override
    public EquipmentDTO convert(EquipmentRequestCreate request) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setFirstname(request.getFirstname());
        equipmentDTO.setLastname(request.getLastname());
        equipmentDTO.setPhone(request.getPhone());
        equipmentDTO.setLoading_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime().plusHours(12)));
        equipmentDTO.setStock_index(Math.toIntExact(Long.valueOf(randomValuesGenerator.generateRandomString())));
        equipmentDTO.setDistance(request.getDistance());
        equipmentDTO.setValidity(Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().plusDays(3)));
        equipmentDTO.setAddress(addressDataService.findAddressById(request.getAddress_id()));
        return equipmentDTO;
    }
}
