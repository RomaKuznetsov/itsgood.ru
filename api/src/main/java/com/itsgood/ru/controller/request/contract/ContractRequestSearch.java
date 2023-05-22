package com.itsgood.ru.controller.request.contract;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Validated
public class ContractRequestSearch {
@NotNull
    private int id;
}
