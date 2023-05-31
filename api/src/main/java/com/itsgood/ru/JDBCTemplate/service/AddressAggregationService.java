package com.itsgood.ru.JDBCTemplate.service;

import com.itsgood.ru.old.domain.Address;
import com.itsgood.ru.old.domain.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AddressAggregationService {
    Map<Integer, Address> createHashMapAllAddress() throws SQLException;

    List<Address> findListAllAddress() throws SQLException;

    List<Address> findListCustomerDelivery(Customer customer) throws SQLException;

    List<Address> findListAddressOneCustomer(Customer customer) throws SQLException;
}
