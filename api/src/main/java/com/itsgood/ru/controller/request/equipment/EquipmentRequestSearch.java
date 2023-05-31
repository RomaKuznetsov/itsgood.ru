package com.itsgood.ru.controller.request.equipment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class EquipmentRequestSearch {
    @Size(min = 450000)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp loading_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp shipment_time;
    @Size(min = 150000)
    private int Address_id;
}
