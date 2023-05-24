package com.itsgood.ru.service;

import com.itsgood.ru.controller.request.bucket.BucketRequestCreate;
import com.itsgood.ru.controller.request.bucket.BucketRequestSearch;
import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.converters.BucketConverterRequestCreate;
import com.itsgood.ru.converters.BucketConverterRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.repository.BucketDataRepository;
import com.itsgood.ru.repository.ContractDataRepository;
import com.itsgood.ru.repository.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class BucketDataService {
    private final CustomerDataService customerDataService;
    private final ContractDataService contractDataService;
    private final BucketDataRepository bucketDataRepository;
    private final ContractDataRepository contractDataRepository;
    private final BucketConverterRequestCreate bucketConverterRequestCreate;
    private final BucketConverterRequestUpdate bucketConverterRequestUpdate;

    public BucketDTO findBucketById(Integer id) {
        Optional<BucketDTO> searchResult = bucketDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Cacheable("bucket")
    public List<BucketDTO> findAllBucket() {
        return bucketDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public BucketDTO createBucket(BucketRequestCreate request) {
        return bucketConverterRequestCreate.convert(request);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public Set<BucketDTO> updateBucket(BucketRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        ContractDTO contractDTO = customerDTO.getContract();
        Set<BucketDTO> updateBuckets = bucketConverterRequestUpdate.convert(request);
        contractDTO.setBuckets(updateBuckets);
        contractDTO = contractDataRepository.save(contractDTO);
        return contractDTO.getBuckets();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteBucketById(Integer id) {
        bucketDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteAllBucketsByCustomerId(BucketRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findCustomerById(request.getCustomer_id());
        Set<BucketDTO> deleteBuckets = customerDTO.getContract().getBuckets();
        bucketDataRepository.deleteAll(deleteBuckets);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteAllBucketsByAuthenticate() {
        Set<BucketDTO> deleteBuckets = contractDataService.findSetBucket();
        bucketDataRepository.deleteAll(deleteBuckets);
    }
}
