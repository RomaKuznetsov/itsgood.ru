package com.itsgood.ru.controller.request.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
@Getter
@RequiredArgsConstructor
public class DeliveryRequestSearch {

    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp loading_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp shipment_time;
    private int Address_id;
}
