package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.Contract_itemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Contract_itemDataRepository extends JpaRepository<Contract_itemDTO, Integer>,
        PagingAndSortingRepository<Contract_itemDTO, Integer>,
        CrudRepository<Contract_itemDTO, Integer> {
}
