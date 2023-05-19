package com.itsgood.ru.hibernate.repository;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;
import java.util.List;

public interface HibernateDeliveryRepository extends CRUDRepository<Integer, HibernateDelivery> {

    HibernateContract findDeliveryListItem() throws SQLException;
}
