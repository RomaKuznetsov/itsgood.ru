package com.itsgood.ru.service;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;

import java.sql.SQLException;

public interface AddressService {
    Address findCustomerRegistration(Customer customer) throws SQLException;

    Address findOne(Integer id) throws SQLException;

    Address create(Address address) throws SQLException;

    Address update(Address addressDTO) throws SQLException;

    void delete(Integer id) throws SQLException;
}

