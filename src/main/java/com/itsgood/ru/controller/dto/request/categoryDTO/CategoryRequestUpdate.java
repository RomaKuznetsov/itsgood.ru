package com.itsgood.ru.controller.dto.request.categoryDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class CategoryRequestUpdate {
    @NotNull
    private int id;
    @Size(max = 20)
    private String title;
    @Size(max = 3500)
    private String description;
}
