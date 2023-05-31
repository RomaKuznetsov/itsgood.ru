package com.itsgood.ru.JDBCTemplate.service;

import com.itsgood.ru.old.domain.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerAggregationService {
    List<Customer> createListAllCustomer() throws SQLException;

    Map<Integer, Customer> createHashMapAllCustomer() throws SQLException;
}
