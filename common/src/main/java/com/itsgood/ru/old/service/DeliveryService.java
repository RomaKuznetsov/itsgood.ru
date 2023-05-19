package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Delivery;

import java.sql.SQLException;

public interface DeliveryService {

    Delivery createDeliveryCustomerParameters(Customer customer, Delivery delivery) throws SQLException;
}
