package com.itsgood.ru.converters.impl.bucket;

import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.converters.BucketConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.BucketDTO;
import com.itsgood.ru.service.spring.ContractDataService;
import com.itsgood.ru.service.spring.EquipmentDataService;
import com.itsgood.ru.service.spring.ItemDataService;
import com.itsgood.ru.repository.spring.BucketDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BucketConverterRequestUpdateImpl implements BucketConverterRequestUpdate {
    private final ItemDataService itemDataService;
    private final ContractDataService contractDataService;
    private final EquipmentDataService equipmentDataService;
    private final BucketDataRepository bucketDataRepository;
    @Override
    public BucketDTO convert(BucketRequestUpdate request) {
        Optional<BucketDTO> searchResult = bucketDataRepository.findById(request.getId());
        BucketDTO bucketDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        bucketDTO.setContract(contractDataService.findContractByAuthenticate());
        bucketDTO.setItem(itemDataService.findItemById(request.getItem_id()));
        bucketDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        bucketDTO.setEquipment(equipmentDataService.findEquipmentById(request.getEquipment_id()));
        return bucketDTO;
    }
}
