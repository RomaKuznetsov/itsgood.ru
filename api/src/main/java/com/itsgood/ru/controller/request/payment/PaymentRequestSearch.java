package com.itsgood.ru.controller.request.payment;

import com.itsgood.ru.codes.StatusPayment;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Validated
public class PaymentRequestSearch {
private int id;
    private String status;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validation;
    @DecimalMax(value = "9999999999999999")
    @DecimalMin(value = "1000000000000000")
    private BigInteger card;
}
