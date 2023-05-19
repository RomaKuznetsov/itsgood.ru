package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerAggregationService {
    List<Customer> createListAllCustomer() throws SQLException;

    Map<Integer, Customer> createHashMapAllCustomer() throws SQLException;
}
