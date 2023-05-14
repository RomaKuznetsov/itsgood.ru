package com.itsgood.ru.controller.dto.request.addressDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Validated
public class AddressRequestSearch {
    @NotNull
    private int id;


}
