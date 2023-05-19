package com.itsgood.ru.repository;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;

public interface CustomerRepository extends CRUDRepository<Integer, Customer> {
    Customer findCustomerByMail(String mail) throws SQLException;
    Customer findCustomerByUsernameMail(Customer customer) throws SQLException;
    Customer findCustomerByUsername(String username) throws SQLException;

    Customer findMaxIdCustomer() throws SQLException;
    Customer findMinIdCustomer() throws SQLException;
}
