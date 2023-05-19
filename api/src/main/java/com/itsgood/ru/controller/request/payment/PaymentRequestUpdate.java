package com.itsgood.ru.controller.request.payment;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Validated
public class PaymentRequestUpdate {

    private int id;
    private String status;
    @NotNull
    @Pattern(regexp = "//d{9}")
    private int phone;
}
