package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.old.domain.Equipment;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.DeliveryColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;


@Component
public class DeliveryRowMapper implements RowMapper<Equipment> {
    @Override
    public Equipment mapRow(ResultSet rs, int rowNum) {
        Equipment equipment;
        try {
            equipment = new Equipment();
            equipment.setId(rs.getInt(DeliveryColumns.ID));
            equipment.setAddress_id(rs.getInt(DeliveryColumns.ADDRESS_ID));
            equipment.setFirstname(rs.getString(DeliveryColumns.FIRSTNAME));
            equipment.setLastname(rs.getString(DeliveryColumns.LASTNAME));
            equipment.setPhone(rs.getInt(DeliveryColumns.PHONE));
            equipment.setLoading_time(rs.getString(DeliveryColumns.LOADING_TIME));
            equipment.setShipment_time(rs.getString(DeliveryColumns.SHIPMENT_TIME));
            equipment.setStock_index(rs.getInt(DeliveryColumns.STOCK_INDEX));
            equipment.setDistance(rs.getInt(DeliveryColumns.DISTANCE));
            equipment.setPrice(rs.getInt(DeliveryColumns.PRICE));
            equipment.setValidity(rs.getString(DeliveryColumns.VALIDITY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return equipment;
    }
}
