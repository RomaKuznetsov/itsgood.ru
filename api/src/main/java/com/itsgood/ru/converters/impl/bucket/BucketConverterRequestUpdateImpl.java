package com.itsgood.ru.converters.impl.bucket;

import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.converters.BucketConverterRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.EquipmentDTO;
import com.itsgood.ru.service.spring.ContractDataService;
import com.itsgood.ru.service.spring.EquipmentDataService;
import com.itsgood.ru.repository.spring.BucketDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BucketConverterRequestUpdateImpl implements BucketConverterRequestUpdate {
    private final ContractDataService contractDataService;
    private final EquipmentDataService equipmentDataService;
    private final BucketDataRepository bucketDataRepository;

    @Override
    public Set<BucketDTO> convert(BucketRequestUpdate request) {
        Set<BucketDTO> searchResult = contractDataService.findSetBucket();
        EquipmentDTO equipmentDTO = equipmentDataService.findEquipmentById(request.getEquipment_id());
        for (BucketDTO bucket : searchResult) {
            bucket.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
            bucket.setEquipment(equipmentDTO);
            bucketDataRepository.save(bucket);
        }
        return searchResult;
    }
}
