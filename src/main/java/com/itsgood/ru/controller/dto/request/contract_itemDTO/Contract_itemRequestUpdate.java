package com.itsgood.ru.controller.dto.request.contract_itemDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class Contract_itemRequestUpdate {
    @NotNull
    private int id;

    private int item_id;

    private int delivery_id;

    private int contract_id;
}
