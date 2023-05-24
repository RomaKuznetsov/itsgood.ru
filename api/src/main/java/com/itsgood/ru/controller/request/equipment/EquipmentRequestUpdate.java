package com.itsgood.ru.controller.request.equipment;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Validated
public class EquipmentRequestUpdate {

    private int id;
    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
//    @Pattern(regexp = "\\d{9}")
    private int phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp shipment_time;
    private int stock_index;
    private String distance;
    @Positive
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;
    private int address_id;

}
