package com.itsgood.ru.repository;


import com.itsgood.ru.domain.Delivery;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;
import java.util.Map;

public interface DeliveryRepository extends CRUDRepository<Integer, Delivery> {
    Delivery findMaxIdDelivery() throws SQLException;
    Delivery findMinIdDelivery() throws SQLException;
    Delivery createDeliveryCustomer(Map<String, Object> parameters) throws SQLException;
}
