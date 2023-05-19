package com.itsgood.ru.domain;

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
public class Contract {

    private int id;
    private int customer_id;
    private int address_id;
    private int payment_id;
    private int sum_order;
    private String payment_types;
    private String relevance;
    private String create_time;
    private String update_time;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
