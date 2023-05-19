package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerService {

    Customer findCustomer(Customer customer) throws SQLException;

    Optional<Customer> findCustomerByUsername(String username) throws SQLException;

    Customer findCustomerByMail(String mail) throws SQLException;

    Customer findOne(Integer id) throws SQLException;

    Customer create(Customer customer) throws SQLException;

    Customer update(Customer customer) throws SQLException;

    void delete(Integer id) throws SQLException;
}
