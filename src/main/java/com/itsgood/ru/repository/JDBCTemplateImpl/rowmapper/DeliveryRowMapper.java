package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Delivery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.DeliveryColumns.*;


@Component
public class DeliveryRowMapper implements RowMapper<Delivery> {
    @Override
    public Delivery mapRow(ResultSet rs, int rowNum) {
        Delivery delivery;
        try {
            delivery = new Delivery();
            delivery.setId(rs.getInt(ID));
            delivery.setAddress_id(rs.getInt(ADDRESS_ID));
            delivery.setFirstname(rs.getString(FIRSTNAME));
            delivery.setLastname(rs.getString(LASTNAME));
            delivery.setPhone(rs.getInt(PHONE));
            delivery.setLoading_time(rs.getString(LOADING_TIME));
            delivery.setShipment_time(rs.getString(SHIPMENT_TIME));
            delivery.setStock_index(rs.getInt(STOCK_INDEX));
            delivery.setDistance(rs.getInt(DISTANCE));
            delivery.setPrice(rs.getInt(PRICE));
            delivery.setValidity(rs.getString(VALIDITY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return delivery;
    }
}
