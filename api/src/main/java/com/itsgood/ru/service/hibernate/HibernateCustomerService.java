package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;

import java.sql.SQLException;
import java.util.List;

public interface HibernateCustomerService {

    HibernateCustomer findOne(Integer id) throws SQLException;

    List<HibernateCustomer> findAll() throws SQLException;

    HibernateCustomer create(HibernateCustomer object) throws SQLException;

    HibernateCustomer update(HibernateCustomer object) throws SQLException;

    void delete(Integer id) throws SQLException;
}
