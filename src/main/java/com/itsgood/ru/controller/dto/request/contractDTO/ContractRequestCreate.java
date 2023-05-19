package com.itsgood.ru.controller.dto.request.contractDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Validated
public class ContractRequestCreate {
    private int sum_order = 0;
    @NotNull
    private String payment_types = "cash";
    @NotNull
    private String relevance = "relevance";
    private int address_id;
    private int payment_id;
}
