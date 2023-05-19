package com.itsgood.ru.hibernate.service;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.hibernate.domain.HibernateItem;

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
