package com.itsgood.ru.old.repository.hibernate;


import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

public interface HibernateItemRepository extends CRUDRepository<Integer, ItemDTO> {
}
