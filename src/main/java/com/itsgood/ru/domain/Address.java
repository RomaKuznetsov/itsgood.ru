package com.itsgood.ru.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Address {
    private int id;
    private int customer_id;
    private String code;
    private String country;
    private String region;
    private String city;
    private String street;
    private int house;
    private String frame;
    private int apartment;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return customer_id == address.customer_id && house == address.house && apartment == address.apartment && Objects.equals(code, address.code) && Objects.equals(country, address.country) && Objects.equals(region, address.region) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(frame, address.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, code, country, region, city, street, house, frame, apartment);
    }
}
