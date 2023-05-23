package com.itsgood.ru.controller.request.address;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Validated
public class AddressRequestUpdate {
    @NotNull
    private int id;
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
    @Positive
    private int house;
    private String frame;
    private int apartment;

}
