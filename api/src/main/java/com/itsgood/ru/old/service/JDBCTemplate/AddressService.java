package com.itsgood.ru.old.service.JDBCTemplate;

import com.itsgood.ru.old.domain.Address;
import com.itsgood.ru.old.domain.Customer;

import java.sql.SQLException;

public interface AddressService {
    Address findCustomerRegistration(Customer customer) throws SQLException;

    Address findOne(Integer id) throws SQLException;

    Address create(Address address) throws SQLException;

    Address update(Address addressDTO) throws SQLException;

    void delete(Integer id) throws SQLException;
}

