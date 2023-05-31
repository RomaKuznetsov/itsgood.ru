package com.itsgood.ru.controller.request.equipment;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Validated
public class EquipmentRequestUpdate {
    @Size(min = 450000)
    private int id;
    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
    @DecimalMax(value = "999999999")
    @DecimalMin(value = "100000000")
    private BigInteger phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp shipment_time;
    private int stock_index;
    private String distance;
    @Positive
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;
    @Size(min = 150000)
    private int address_id;

}
