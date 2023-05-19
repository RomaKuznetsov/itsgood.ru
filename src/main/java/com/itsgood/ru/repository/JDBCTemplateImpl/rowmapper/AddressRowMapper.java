package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Address;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.CUSTOMER_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.CODE;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.COUNTRY;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.REGION;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.CITY;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.STREET;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.HOUSE;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.FRAME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.AddressColumns.APARTMENT;

@Component
public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) {

        Address address;
        try {
            address = new Address();
            address.setId(rs.getInt(ID));
            address.setCustomer_id(rs.getInt(CUSTOMER_ID));
            address.setCode(rs.getString(CODE));
            address.setCountry(rs.getString(COUNTRY));
            address.setRegion(rs.getString(REGION));
            address.setCity(rs.getString(CITY));
            address.setStreet(rs.getString(STREET));
            address.setHouse(rs.getInt(HOUSE));
            address.setFrame(rs.getString(FRAME));
            address.setApartment(rs.getInt(APARTMENT));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }
}
