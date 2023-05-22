package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestCreate;
import com.itsgood.ru.controller.request.equipment.EquipmentRequestSearch;
import com.itsgood.ru.controller.request.equipment.EquipmentRequestUpdate;
import com.itsgood.ru.converters.EquipmentConverterRequestCreate;
import com.itsgood.ru.converters.EquipmentConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.EquipmentDTO;
import com.itsgood.ru.repository.spring.EquipmentDataRepository;
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
public class EquipmentDataService {

    private final AddressDataService addressDataService;
    private final CustomerDataService customerDataService;
    private final EquipmentDataRepository equipmentDataRepository;
    private final EquipmentConverterRequestUpdate equipmentConverterRequestUpdate;
    private final EquipmentConverterRequestCreate equipmentConverterRequestCreate;

    public EquipmentDTO findEquipmentById(Integer id) {
        Optional<EquipmentDTO> searchResult = equipmentDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<EquipmentDTO> findAll() {
        return equipmentDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public EquipmentDTO createEquipment(EquipmentRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        EquipmentDTO equipmentDTO = equipmentConverterRequestCreate.convert(request);
        if (customerDTO.getAddress().contains(equipmentDTO.getAddress())) {
            equipmentDTO = equipmentDataRepository.save(equipmentDTO);
        } else throw new EntityNotFoundException("This shipping address does not exist");
        return equipmentDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public EquipmentDTO updateEquipment(EquipmentRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        EquipmentDTO equipmentDTO = equipmentConverterRequestUpdate.convert(request);
        if (customerDTO.getAddress().contains(equipmentDTO.getAddress())) {
            equipmentDataRepository.save(equipmentDTO);
        } else throw new EntityNotFoundException("This shipping address does not exist");
        return equipmentDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteEquipment(EquipmentRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        EquipmentDTO equipmentDTO = equipmentConverterRequestUpdate.convert(request);
        if (customerDTO.getAddress().contains(addressDataService.
                findAddressById(request.getAddress_id()))) {
            equipmentDataRepository.delete(equipmentDTO);
        } else throw new EntityNotFoundException("This shipping address does not exist");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteEquipmentById(EquipmentRequestSearch request) {
        equipmentDataRepository.deleteById(request.getId());
    }

}
