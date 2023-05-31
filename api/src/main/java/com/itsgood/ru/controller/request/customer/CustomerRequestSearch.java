package com.itsgood.ru.controller.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Validated
@NoArgsConstructor
public class CustomerRequestSearch {
    @Size(min = 100000)
    private int id;
    @Email
    private String mail;
}
