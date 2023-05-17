package com.itsgood.ru.controller.dto.request.paymentDTO;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.util.UUID;

@Getter
@Validated
public class PaymentRequestCreate {

    private String status;
    @NotNull
    @Pattern(regexp = "//d{9}")
    private int phone;
    @Pattern(regexp = "//d{16}")
    private String card;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;

}
