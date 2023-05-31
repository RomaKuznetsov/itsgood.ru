package com.itsgood.ru.controller.request.item;

import com.itsgood.ru.domain.Volume;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Validated
@Schema(title = "Item whit information about create")
public class ItemRequestCreate {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Notebook", type = "string",
            description = "item first title")
    @NotNull
    @Size(min = 3, max = 20) //нужно синхронизировать размер данных с базой
    private String title;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "0", type = "integer",
            description = "item price")
    @NotNull
    @Positive
    private int price;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Hp", type = "string",
            description = "item first firm")
    @Size(min = 2, max = 100)
    private String firm;
    @Pattern(regexp = "http://www.\\.+(com|ru)")
    private String link;
    @Size(max = 3500)
    private String description;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "70", type = "integer",
            description = "item weight")
    @NotNull
    private int weight;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "not_selected", type = "Volume",
            description = "item volume")
    @NotNull
    private Volume volume;
    @NotNull
    @Size(min = 350000)
    private int category_id;


}
