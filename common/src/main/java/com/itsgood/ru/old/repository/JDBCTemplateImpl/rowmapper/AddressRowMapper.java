package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.AddressColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) {

        Address address;
        try {
            address = new Address();
            address.setId(rs.getInt(AddressColumns.ID));
            address.setCustomer_id(rs.getInt(AddressColumns.CUSTOMER_ID));
            address.setCode(rs.getString(AddressColumns.CODE));
            address.setCountry(rs.getString(AddressColumns.COUNTRY));
            address.setRegion(rs.getString(AddressColumns.REGION));
            address.setCity(rs.getString(AddressColumns.CITY));
            address.setStreet(rs.getString(AddressColumns.STREET));
            address.setHouse(rs.getInt(AddressColumns.HOUSE));
            address.setFrame(rs.getString(AddressColumns.FRAME));
            address.setApartment(rs.getInt(AddressColumns.APARTMENT));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }
}
