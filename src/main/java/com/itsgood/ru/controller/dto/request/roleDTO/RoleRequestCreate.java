package com.itsgood.ru.controller.dto.request.roleDTO;

import com.itsgood.ru.enums.RoleCustomer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Validated
public class RoleRequestCreate {

    @NotNull
    private String role = RoleCustomer.ROLE_PERSON_USER.getRole();
    private int customer_id;
    @Positive
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validity = Date.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()).
                 toLocalDate().plusYears(1));
}
