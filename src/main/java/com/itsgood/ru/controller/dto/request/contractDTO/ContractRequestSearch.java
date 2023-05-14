package com.itsgood.ru.controller.dto.request.contractDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Validated
public class ContractRequestSearch {
@NotNull
    private int id;

    private String relevance;
}
