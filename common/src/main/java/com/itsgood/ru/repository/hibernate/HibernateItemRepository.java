package com.itsgood.ru.repository.hibernate;


import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

public interface HibernateItemRepository extends CRUDRepository<Integer, ItemDTO> {
}
