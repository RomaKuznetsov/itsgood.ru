package com.itsgood.ru.controller.dto.request.itemDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Getter
@Validated
public class ItemRequestSearch {

    private int id;
    @Size(max = 20)
    private String title;
}
