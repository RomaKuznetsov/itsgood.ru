package com.itsgood.ru.old.service.JDBCTemplate;

import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.domain.Equipment;

import java.sql.SQLException;

public interface DeliveryService {

    Equipment createDeliveryCustomerParameters(Customer customer, Equipment equipment) throws SQLException;
}
