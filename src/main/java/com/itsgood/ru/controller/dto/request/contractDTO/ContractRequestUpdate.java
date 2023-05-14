package com.itsgood.ru.controller.dto.request.contractDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Getter
@Validated
public class ContractRequestUpdate {

    private int id;

    private String payment_types;

    private String relevance;
}
