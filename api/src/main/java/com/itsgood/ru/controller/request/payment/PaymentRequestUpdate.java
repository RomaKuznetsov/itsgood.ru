package com.itsgood.ru.controller.request.payment;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Getter
@Validated
public class PaymentRequestUpdate {
    @NotNull
    @Size(min = 250000)
    private int id;
    @NotNull
    private String status;
    @DecimalMax(value = "9999999999999999")
    @DecimalMin(value = "1000000000000000")
    private BigInteger phone;
}
