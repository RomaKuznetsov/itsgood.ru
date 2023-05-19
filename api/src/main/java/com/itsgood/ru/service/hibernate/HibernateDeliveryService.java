package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateContract;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;


import java.sql.SQLException;
import java.util.List;

public interface HibernateDeliveryService {
    HibernateDelivery findOne(Integer id) throws SQLException;

    List<HibernateDelivery> findAll() throws SQLException;

    HibernateDelivery create(HibernateDelivery delivery) throws SQLException;

    HibernateDelivery update(HibernateDelivery delivery) throws SQLException;

    void delete(Integer id) throws SQLException;

    HibernateContract findDeliveryListItem() throws SQLException;


}
