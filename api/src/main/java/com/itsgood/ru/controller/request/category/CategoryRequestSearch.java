package com.itsgood.ru.controller.request.category;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Getter
@Validated
public class CategoryRequestSearch {
    @Size(min = 350000)
    private int id;
    @Size(max = 20)
    private String title;
}
