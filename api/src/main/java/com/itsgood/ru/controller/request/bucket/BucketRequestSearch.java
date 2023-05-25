package com.itsgood.ru.controller.request.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class BucketRequestSearch {
    @Size(min = 500000)
    private int id;
    @Size(min = 100000)
    private int customer_id;
}
