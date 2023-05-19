package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface HibernateCustomerService {

    CustomerDTO findOne(Integer id) throws SQLException;

    List<CustomerDTO> findAll() throws SQLException;

    CustomerDTO create(CustomerDTO object) throws SQLException;

    CustomerDTO update(CustomerDTO object) throws SQLException;

    void delete(Integer id) throws SQLException;
}
