package com.itsgood.ru.controller.request.equipment;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Validated
@Getter
public class EquipmentRequestCreate {

    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
    @DecimalMax(value = "999999999")
    @DecimalMin(value = "100000000")
    private BigInteger phone;

    private int address_id;

    private String distance;
    @Positive
    private int price;

}
