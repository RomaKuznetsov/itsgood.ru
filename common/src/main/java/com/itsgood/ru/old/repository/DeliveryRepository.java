package com.itsgood.ru.old.repository;


import com.itsgood.ru.domain.Delivery;

import java.sql.SQLException;
import java.util.Map;

public interface DeliveryRepository extends CRUDRepository<Integer, Delivery> {
    Delivery findMaxIdDelivery() throws SQLException;
    Delivery findMinIdDelivery() throws SQLException;
    Delivery createDeliveryCustomer(Map<String, Object> parameters) throws SQLException;
}
