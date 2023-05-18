package com.itsgood.ru.controller.dto.request.addressDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class AddressRequestCreate {

//    private int customer_id;
    @NotNull
    private String code;
    @NotNull
    private String country;
    @NotNull
    private String region;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    @Size(min = 1, max = 5)
    private int house;
    private String frame;
    @NotNull
    @Size(min = 1, max = 5)
    private int apartment;
}
