package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.old.domain.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.RoleColumns.*;


@Component
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) {

        Role role;

        try {
            role = new Role();
            role.setId(rs.getInt(ID));
            role.setRole(rs.getString(ROLE));
            role.setCustomer_id(rs.getInt(CUSTOMER_ID));
            role.setCreate_time(rs.getString(CREATE_TIME));
            role.setUpdate_time(rs.getString(UPDATE_TIME));
            role.setValidity(rs.getString(VALIDITY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }
}
