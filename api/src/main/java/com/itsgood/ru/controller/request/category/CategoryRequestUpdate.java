package com.itsgood.ru.controller.request.category;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class CategoryRequestUpdate {
    @NotNull
    @Size(min = 350000)
    private int id;
    @Size(max = 20)
    private String title;
    @Size(max = 3500)
    private String description;
}
