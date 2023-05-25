package com.itsgood.ru.controller.request.contract;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
public class ContractRequestCreate {
    @NotNull
    private String payment_types = "cash";
    @NotNull
    @Size(min = 100000)
    private int customer_id;
}
