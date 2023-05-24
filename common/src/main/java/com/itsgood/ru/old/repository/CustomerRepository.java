package com.itsgood.ru.old.repository;

import com.itsgood.ru.old.domain.Customer;

import java.sql.SQLException;

public interface CustomerRepository extends CRUDRepository<Integer, Customer> {
    Customer findCustomerByMail(String mail) throws SQLException;
    Customer findCustomerByUsernameMail(Customer customer) throws SQLException;
    Customer findCustomerByUsername(String username) throws SQLException;

    Customer findMaxIdCustomer() throws SQLException;
    Customer findMinIdCustomer() throws SQLException;
}
