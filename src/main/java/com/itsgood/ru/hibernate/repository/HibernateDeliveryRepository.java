package com.itsgood.ru.hibernate.repository;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.repository.CRUDRepository;

import java.sql.SQLException;

public interface HibernateDeliveryRepository extends CRUDRepository<Integer, HibernateDelivery> {

    HibernateContract findDeliveryListItem() throws SQLException;
}
