package com.itsgood.ru.controller.request.contract;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ContractRequestCreate {
    private int sum_order = 0;
}
