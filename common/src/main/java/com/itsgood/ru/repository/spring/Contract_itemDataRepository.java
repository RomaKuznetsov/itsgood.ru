package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateContract_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Contract_itemDataRepository extends JpaRepository<HibernateContract_item, Integer>,
        PagingAndSortingRepository<HibernateContract_item, Integer>,
        CrudRepository<HibernateContract_item, Integer> {
}
