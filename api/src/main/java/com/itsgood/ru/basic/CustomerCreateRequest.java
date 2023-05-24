package com.itsgood.ru.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequest {


    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String mail;
    @NotNull
    private String password;
    private int phone;
    private Date birthday;
    private String gender;
    private Timestamp create_time;
    private Timestamp update_time;
}
