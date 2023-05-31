package com.itsgood.ru.controller.request.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class BucketRequestCreate {
    @NotNull
    @Size(min = 400000)
    private int item_id;
}
