package com.itsgood.ru.repository.hibernate;


import com.itsgood.ru.domain.hibernate.HibernateContract;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import com.itsgood.ru.old.repository.CRUDRepository;

import java.sql.SQLException;

public interface HibernateDeliveryRepository extends CRUDRepository<Integer, HibernateDelivery> {

    HibernateContract findDeliveryListItem() throws SQLException;
}
