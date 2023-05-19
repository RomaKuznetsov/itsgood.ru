package com.itsgood.ru.controller.request.contract_item;

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
