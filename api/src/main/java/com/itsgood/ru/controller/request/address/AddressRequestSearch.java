package com.itsgood.ru.controller.request.address;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class AddressRequestSearch {
    @NotNull
    @Size(min = 150000)
    private int id;
    private String code;


}
