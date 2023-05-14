package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.FIRSTNAME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.LASTNAME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.USERNAME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.MAIL;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.PASSWORD;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.PHONE;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.BIRTHDAY;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.GENDER;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.CREATE_TIME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CustomerColumns.UPDATE_TIME;


@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) {

        Customer customer;

        try {
            customer = new Customer();
            customer.setId(rs.getInt(ID));
            customer.setFirstname(rs.getString(FIRSTNAME));
            customer.setLastname(rs.getString(LASTNAME));
            customer.setUsername(rs.getString(USERNAME));
            customer.setMail(rs.getString(MAIL));
            customer.setPassword(rs.getString(PASSWORD));
            customer.setPhone(rs.getInt(PHONE));
            customer.setBirthday(rs.getString(BIRTHDAY));
            customer.setGender(rs.getString(GENDER));
            customer.setCreate_time(rs.getString(CREATE_TIME));
            customer.setUpdate_time(rs.getString(UPDATE_TIME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}
