package com.itsgood.ru.hibernate.service;

import com.itsgood.ru.hibernate.domain.HibernateItem;

import java.sql.SQLException;
import java.util.List;


public interface HibernateItemService {

    HibernateItem findOne(Integer id) throws SQLException;

    List<HibernateItem> findAll() throws SQLException;

    HibernateItem create(HibernateItem object) throws SQLException;

    HibernateItem update(HibernateItem object) throws SQLException;

    void delete(Integer id) throws SQLException;
}
