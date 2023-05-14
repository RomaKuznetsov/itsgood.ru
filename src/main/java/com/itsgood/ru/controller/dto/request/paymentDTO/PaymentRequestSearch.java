package com.itsgood.ru.controller.dto.request.paymentDTO;

import com.itsgood.ru.enums.StatusPayment;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;

@Getter
@Validated
public class PaymentRequestSearch {
private int id;
    private String status = StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validation;
}
