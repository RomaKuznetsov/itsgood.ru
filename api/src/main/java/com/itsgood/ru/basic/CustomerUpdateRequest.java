package com.itsgood.ru.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest extends CustomerCreateRequest {
    private Long id;
}
