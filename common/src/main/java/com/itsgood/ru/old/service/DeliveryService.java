package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Equipment;

import java.sql.SQLException;

public interface DeliveryService {

    Equipment createDeliveryCustomerParameters(Customer customer, Equipment equipment) throws SQLException;
}
