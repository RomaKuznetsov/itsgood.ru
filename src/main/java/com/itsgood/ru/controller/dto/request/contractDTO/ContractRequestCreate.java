package com.itsgood.ru.controller.dto.request.contractDTO;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class ContractRequestCreate {
    private int sum_order = 0;
    @NotNull
    private int customer_id;
}
