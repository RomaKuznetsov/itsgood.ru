package com.itsgood.ru.controller.request.role;

import com.itsgood.ru.codes.RoleCustomer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Validated
public class RoleRequestCreate {

    private String role = RoleCustomer.ROLE_PERSON_USER.getRole();
    @Size(min = 100000)
    private int customer_id;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity;
}
