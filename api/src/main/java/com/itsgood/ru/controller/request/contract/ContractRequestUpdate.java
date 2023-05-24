package com.itsgood.ru.controller.request.contract;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
public class ContractRequestUpdate {

    private int id;
    private String payment_types;
}
