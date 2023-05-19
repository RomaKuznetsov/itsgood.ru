package com.itsgood.ru.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated

public class Customer {
    private int id;
    @Size(min = 2, max = 15)
    private String firstname;
    @Size(min = 2, max = 15)
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String mail;
    @NotNull
    private String password;
    private int phone;
    private String birthday;
    private String gender;
    private String create_time;
    private String update_time;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phone == customer.phone && Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(username, customer.username) && Objects.equals(mail, customer.mail) && Objects.equals(password, customer.password) && Objects.equals(birthday, customer.birthday) && Objects.equals(gender, customer.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, username, mail, password, phone, birthday, gender);
    }
}
