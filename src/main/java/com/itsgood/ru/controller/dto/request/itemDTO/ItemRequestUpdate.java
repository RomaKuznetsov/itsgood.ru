package com.itsgood.ru.controller.dto.request.itemDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class ItemRequestUpdate {

    private int id;
    @NotNull
    @Size(min = 20)
    private String title;
    @NotNull
    private int price;
    @Size(min = 2, max = 100)
    private String firm;

    private String link;
    @Size(max = 3500)
    private String description;
    @NotNull
    private int weight;
    @NotNull
    private String volume;

    private int category_id;


}
