package com.itsgood.ru.repository;


import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Payment;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PaymentRepository extends CRUDRepository<Integer, Payment> {
    List<Payment> findOneCustomer(Customer customer) throws SQLException;
    Payment findOneCustomerActive(Customer customer) throws SQLException;
    Payment findMaxIdPayment() throws SQLException;
    Payment findMinIdPayment() throws SQLException;
    Payment customerCreatePayment (Map<String, Object> parameters) throws SQLException;


    }
