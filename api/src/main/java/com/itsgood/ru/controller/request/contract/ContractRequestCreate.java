package com.itsgood.ru.controller.request.contract;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class ContractRequestCreate {
    @NotNull
    private String payment_types = "cash";
    @NotNull
    private int customer_id;
}
