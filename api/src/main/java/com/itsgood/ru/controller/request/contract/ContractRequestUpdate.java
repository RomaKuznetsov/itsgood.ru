package com.itsgood.ru.controller.request.contract;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;


@Getter
@Validated
public class ContractRequestUpdate {
    @Size(min = 300000)
    private int id;
    private String payment_types;
}
