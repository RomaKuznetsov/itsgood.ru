package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.bucket.BucketRequestCreate;
import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.converters.BucketConverterRequestCreate;
import com.itsgood.ru.converters.BucketConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.BucketDTO;
import com.itsgood.ru.repository.spring.BucketDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class BucketDataService {

    private final ContractDataService contractDataService;
    private final BucketDataRepository bucketDataRepository;
    private final BucketConverterRequestCreate bucketConverterRequestCreate;
    private final BucketConverterRequestUpdate bucketConverterRequestUpdate;

    public BucketDTO findBucketById(Integer id) {
        Optional<BucketDTO> searchResult = bucketDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public BucketDTO createBucket(BucketRequestCreate request) {
        return bucketConverterRequestCreate.convert(request);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public BucketDTO updateBucket(BucketRequestUpdate request) {
        BucketDTO bucketDTO = bucketConverterRequestUpdate.convert(request);
        return bucketDataRepository.save(bucketDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteBucketById(Integer id) {
        bucketDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteBucket(BucketRequestUpdate request) {
        BucketDTO bucketDTO = bucketConverterRequestUpdate.convert(request);
        Set<BucketDTO> searchResult = contractDataService.findSetBucket();
        if (searchResult.contains(bucketDTO)) {
            bucketDataRepository.delete(bucketDTO);
        } else throw new EntityNotFoundException("No such bucket");
    }
}
