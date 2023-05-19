package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Delivery;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.DeliveryColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;


@Component
public class DeliveryRowMapper implements RowMapper<Delivery> {
    @Override
    public Delivery mapRow(ResultSet rs, int rowNum) {
        Delivery delivery;
        try {
            delivery = new Delivery();
            delivery.setId(rs.getInt(DeliveryColumns.ID));
            delivery.setAddress_id(rs.getInt(DeliveryColumns.ADDRESS_ID));
            delivery.setFirstname(rs.getString(DeliveryColumns.FIRSTNAME));
            delivery.setLastname(rs.getString(DeliveryColumns.LASTNAME));
            delivery.setPhone(rs.getInt(DeliveryColumns.PHONE));
            delivery.setLoading_time(rs.getString(DeliveryColumns.LOADING_TIME));
            delivery.setShipment_time(rs.getString(DeliveryColumns.SHIPMENT_TIME));
            delivery.setStock_index(rs.getInt(DeliveryColumns.STOCK_INDEX));
            delivery.setDistance(rs.getInt(DeliveryColumns.DISTANCE));
            delivery.setPrice(rs.getInt(DeliveryColumns.PRICE));
            delivery.setValidity(rs.getString(DeliveryColumns.VALIDITY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return delivery;
    }
}
