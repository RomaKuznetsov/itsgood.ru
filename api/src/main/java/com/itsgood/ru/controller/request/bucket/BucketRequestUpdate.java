package com.itsgood.ru.controller.request.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class BucketRequestUpdate {
    @NotNull
    private int id;

    private int item_id;

    private int equipment_id;

}
