package com.itsgood.ru.old.domain;

import com.itsgood.ru.domain.Volume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Item {

    private int id;
    private String  title;
    private int price;
    private String firm;
    private String link;
    private String description;
    private int weight;
    private Volume volume;
    private String create_time;
    private String update_time;
    private int  category_id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
