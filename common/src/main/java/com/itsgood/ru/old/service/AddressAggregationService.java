package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AddressAggregationService {
    Map<Integer, Address> createHashMapAllAddress() throws SQLException;

    List<Address> findListAllAddress() throws SQLException;

    List<Address> findListCustomerDelivery(Customer customer) throws SQLException;

    List<Address> findListAddressOneCustomer(Customer customer) throws SQLException;
}
