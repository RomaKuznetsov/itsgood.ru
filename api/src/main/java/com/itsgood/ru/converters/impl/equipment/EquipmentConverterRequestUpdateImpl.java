package com.itsgood.ru.converters.impl.equipment;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestUpdate;
import com.itsgood.ru.converters.EquipmentConverterRequestUpdate;
import com.itsgood.ru.domain.EquipmentDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.service.AddressDataService;
import com.itsgood.ru.repository.EquipmentDataRepository;
import com.itsgood.ru.service.ContractDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EquipmentConverterRequestUpdateImpl implements EquipmentConverterRequestUpdate {
    private final AddressDataService addressDataService;
    private final ContractDataService contractDataService;
    private final EquipmentDataRepository equipmentDataRepository;

    @Override
    public EquipmentDTO convert(EquipmentRequestUpdate request) {
        Optional<EquipmentDTO> searchResult = equipmentDataRepository.findById(request.getId());
        EquipmentDTO equipmentDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        equipmentDTO.setFirstname(request.getFirstname());
        equipmentDTO.setLastname(request.getLastname());
        equipmentDTO.setPhone(request.getPhone());
        equipmentDTO.setShipment_time(request.getShipment_time());
        equipmentDTO.setStock_index(request.getStock_index());
        equipmentDTO.setDistance(request.getDistance());
        List<ItemDTO> items = contractDataService.findSetItemsByCustomerAuthenticate();
        int price = Math.toIntExact(items.stream().map(item -> item.getPrice()).count());
        equipmentDTO.setPrice(price);
        equipmentDTO.setValidity(request.getValidity());
        equipmentDTO.setAddress(addressDataService.findAddressById(request.getAddress_id()));
        return equipmentDTO;
    }
}

