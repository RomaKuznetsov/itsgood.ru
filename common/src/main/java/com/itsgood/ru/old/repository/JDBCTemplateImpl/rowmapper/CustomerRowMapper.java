package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.CustomerColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) {

        Customer customer;

        try {
            customer = new Customer();
            customer.setId(rs.getInt(CustomerColumns.ID));
            customer.setFirstname(rs.getString(CustomerColumns.FIRSTNAME));
            customer.setLastname(rs.getString(CustomerColumns.LASTNAME));
            customer.setUsername(rs.getString(CustomerColumns.USERNAME));
            customer.setMail(rs.getString(CustomerColumns.MAIL));
            customer.setPassword(rs.getString(CustomerColumns.PASSWORD));
            customer.setPhone(rs.getInt(CustomerColumns.PHONE));
            customer.setBirthday(rs.getString(CustomerColumns.BIRTHDAY));
            customer.setGender(rs.getString(CustomerColumns.GENDER));
            customer.setCreate_time(rs.getString(CustomerColumns.CREATE_TIME));
            customer.setUpdate_time(rs.getString(CustomerColumns.UPDATE_TIME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}
