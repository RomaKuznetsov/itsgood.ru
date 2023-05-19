package com.itsgood.ru.controller.request.role;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Data
@Validated
public class RoleRequestUpdate {
    @NotNull
    private int id;
    private int customer_id;
    private String role;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;
}
