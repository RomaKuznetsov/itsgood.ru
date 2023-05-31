package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.bucket.BucketRequestCreate;
import com.itsgood.ru.controller.request.bucket.BucketRequestSearch;
import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.domain.hibernate.BucketDTO;
import com.itsgood.ru.service.spring.BucketDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/rest/spring/bucket")
@RequiredArgsConstructor
public class BucketDataController {

    private final BucketDataService bucketDataService;

    @GetMapping(value = "/findBucketById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<BucketDTO> findBucketById(@Validated @RequestBody BucketRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(bucketDataService.findBucketById(request.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/createBucket", consumes = {"application/xml", "application/json"})
    public ResponseEntity<BucketDTO> createBucket(@Validated @RequestBody
                                                  BucketRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(bucketDataService.createBucket(request), HttpStatus.CREATED);

    }

    @PatchMapping(value = "/updateBucket", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<BucketDTO>> updateBucket(@Validated @RequestBody BucketRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(bucketDataService.updateBucket(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBucketById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteBucketById(@Validated @RequestBody BucketRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        bucketDataService.deleteBucketById(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAllBucketsByCustomerId", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteAllBucketsByCustomerId(@Validated @RequestBody BucketRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        bucketDataService.deleteAllBucketsByCustomerId(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAllBucketsByAuthenticate", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteAllBucketsByAuthenticate() {
        bucketDataService.deleteAllBucketsByAuthenticate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
