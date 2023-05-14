package com.itsgood.ru.controller.dto.request.categoryDTO;

import javax.validation.constraints.Size;

public class CategoryRequestSearch {

    private int id;
    @Size(max = 20)
    private String title;
}
