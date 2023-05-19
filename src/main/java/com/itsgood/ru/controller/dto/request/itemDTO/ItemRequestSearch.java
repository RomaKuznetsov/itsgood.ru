package com.itsgood.ru.controller.dto.request.itemDTO;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Validated
public class ItemRequestSearch {

    private int id;
    @Size(max = 20)
    private String title;
    @Positive
    private int price;
    @Size(min = 2, max = 100)
    private String firm;
    @Pattern(regexp = "http://www.\\.+(com|ru)")
    private String link;
    @Size(max = 3500)
    private String description;
    private int category_id;


}
