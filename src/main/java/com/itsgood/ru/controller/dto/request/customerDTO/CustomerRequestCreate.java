package com.itsgood.ru.controller.dto.request.customerDTO;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Validated
public class CustomerRequestCreate {
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
    @Pattern(regexp = "//d{9}")
    private int phone;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String gender;

}
