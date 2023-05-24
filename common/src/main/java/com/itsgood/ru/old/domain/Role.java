package com.itsgood.ru.old.domain;

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
public class Role {

    private int id;
    private int customer_id;
    private String role;
    private String create_time;
    private String update_time;
    private String validity;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
