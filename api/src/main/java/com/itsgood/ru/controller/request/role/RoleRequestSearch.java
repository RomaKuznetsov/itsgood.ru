package com.itsgood.ru.controller.request.role;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Validated
public class RoleRequestSearch {

    private int id;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity = Date.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()).toLocalDate());
}
