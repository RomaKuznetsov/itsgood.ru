package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateCategory;

import java.sql.SQLException;
import java.util.List;

public interface HibernateCategoryService {

    HibernateCategory findOne(Integer id) throws SQLException;

    List<HibernateCategory> findAll() throws SQLException;

    HibernateCategory create(HibernateCategory contract) throws SQLException;

    HibernateCategory update(HibernateCategory contract) throws SQLException;

    void delete(Integer id) throws SQLException;
}
