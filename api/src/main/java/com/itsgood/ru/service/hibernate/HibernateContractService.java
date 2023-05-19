package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateContract;

import java.sql.SQLException;
import java.util.List;

public interface HibernateContractService {

    HibernateContract findOne(Integer id) throws SQLException;

    List<HibernateContract> findAll() throws SQLException;

    HibernateContract create(HibernateContract contract) throws SQLException;

    HibernateContract update(HibernateContract contract) throws SQLException;

    void delete(Integer id) throws SQLException;
}
