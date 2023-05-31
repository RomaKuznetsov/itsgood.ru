package com.itsgood.ru.controller.request.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class BucketRequestUpdate {
    @NotNull
    @Size(min = 450000)
    private int equipment_id;

}
