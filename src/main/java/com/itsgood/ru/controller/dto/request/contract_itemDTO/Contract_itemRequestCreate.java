package com.itsgood.ru.controller.dto.request.contract_itemDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Contract_itemRequestCreate {
    private int item_id;
    private int contract_id;
}
