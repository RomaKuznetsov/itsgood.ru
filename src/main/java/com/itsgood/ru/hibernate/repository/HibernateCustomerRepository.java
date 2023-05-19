package com.itsgood.ru.hibernate.repository;

import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

public interface HibernateCustomerRepository extends CRUDRepository<Integer, HibernateCustomer> {
}
