package com.itsgood.ru.controller.request.customer;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Validated
public class CustomerRequestUpdate {
    @Size(min = 100000)
    private int id;
    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
    @NotNull
    @Size(min = 2, max = 100)
    private String username;
    @NotNull
    @Email
    private String mail;
    @NotNull
    private String password;
    @DecimalMax(value = "999999999")
    @DecimalMin(value = "100000000")
    private BigInteger phone;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String gender;

}
