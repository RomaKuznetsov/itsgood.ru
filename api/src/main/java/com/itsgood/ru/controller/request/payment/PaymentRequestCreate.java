package com.itsgood.ru.controller.request.payment;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Validated
public class PaymentRequestCreate {

    @NotNull
    @DecimalMax(value = "999999999")
    @DecimalMin(value = "100000000")
    private BigInteger phone;
    @DecimalMax(value = "9999999999999999")
    @DecimalMin(value = "1000000000000000")
    private BigInteger card;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;
}
