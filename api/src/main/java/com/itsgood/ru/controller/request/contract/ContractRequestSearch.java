package com.itsgood.ru.controller.request.contract;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class ContractRequestSearch {
@NotNull
@Size(min = 300000)
    private int id;
}
