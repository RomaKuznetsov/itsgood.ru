package com.itsgood.ru.repository.hibernate;


import com.itsgood.ru.domain.hibernate.CategoryDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

public interface HibernateCategoryRepository extends CRUDRepository<Integer, CategoryDTO> {
}
