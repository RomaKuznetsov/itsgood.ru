package com.itsgood.ru.controller.dto.request.customerDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
@Getter
@Validated
@NoArgsConstructor
public class CustomerRequestSearch {

    private int id;
    @Email
    private String mail;
}
