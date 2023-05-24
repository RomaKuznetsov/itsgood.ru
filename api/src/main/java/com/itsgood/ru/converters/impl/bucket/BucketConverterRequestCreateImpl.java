package com.itsgood.ru.converters.impl.bucket;

import com.itsgood.ru.controller.request.bucket.BucketRequestCreate;
import com.itsgood.ru.converters.BucketConverterRequestCreate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.service.ContractDataService;
import com.itsgood.ru.service.ItemDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class BucketConverterRequestCreateImpl implements BucketConverterRequestCreate {
    private final ItemDataService itemDataService;
    private final ContractDataService contractDataService;
    @Override
    public BucketDTO convert(BucketRequestCreate request) {
        BucketDTO bucketDTO = new BucketDTO();
        ItemDTO itemDTO = itemDataService.findItemById(request.getItem_id());
        bucketDTO.setItem(itemDTO);
        ContractDTO contractDTO = contractDataService.findContractByAuthenticate();
        bucketDTO.setContract(contractDTO);
        bucketDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return bucketDTO;
    }
}
