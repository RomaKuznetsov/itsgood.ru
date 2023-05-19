package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateItem;

import java.sql.SQLException;
import java.util.List;


public interface HibernateItemService {

    HibernateItem findOne(Integer id) throws SQLException;

    List<HibernateItem> findAll() throws SQLException;

    HibernateItem create(HibernateItem object) throws SQLException;

    HibernateItem update(HibernateItem object) throws SQLException;

    void delete(Integer id) throws SQLException;
}
