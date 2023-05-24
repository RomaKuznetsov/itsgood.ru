package com.itsgood.ru.controller.request.equipment;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@Validated
@Getter
public class EquipmentRequestCreate {

    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
//    @Pattern(regexp = "\\d{9}")
    private int phone;

    private int address_id;

    private String distance;
    @Positive
    private int price;

}
