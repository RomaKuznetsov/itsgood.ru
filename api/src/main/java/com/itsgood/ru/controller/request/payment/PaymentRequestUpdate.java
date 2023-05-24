package com.itsgood.ru.controller.request.payment;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Validated
public class PaymentRequestUpdate {
    @NotNull
    private int id;
    @NotNull
    private String status;
    //    @Pattern(regexp = "\\d{9}")
    private int phone;
}
