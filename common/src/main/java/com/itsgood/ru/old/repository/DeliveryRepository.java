package com.itsgood.ru.old.repository;


import com.itsgood.ru.old.domain.Equipment;

import java.sql.SQLException;
import java.util.Map;

public interface DeliveryRepository extends CRUDRepository<Integer, Equipment> {
    Equipment findMaxIdDelivery() throws SQLException;
    Equipment findMinIdDelivery() throws SQLException;
    Equipment createDeliveryCustomer(Map<String, Object> parameters) throws SQLException;
}
