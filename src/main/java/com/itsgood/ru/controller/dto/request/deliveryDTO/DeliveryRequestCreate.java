package com.itsgood.ru.controller.dto.request.deliveryDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Validated
@Getter
public class DeliveryRequestCreate {

    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
    @Pattern(regexp = "[//d{9}]")
    private int phone;

    private int address_id;

    private String distance;

}
