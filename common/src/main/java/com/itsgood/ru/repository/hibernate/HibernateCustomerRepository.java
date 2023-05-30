package com.itsgood.ru.repository.hibernate;


import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

public interface HibernateCustomerRepository extends CRUDRepository<Integer, CustomerDTO> {
}
