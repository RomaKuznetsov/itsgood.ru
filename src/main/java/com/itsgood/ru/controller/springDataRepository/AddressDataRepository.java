package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.HibernateAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AddressDataRepository extends JpaRepository<HibernateAddress, Integer>,
        PagingAndSortingRepository<HibernateAddress, Integer>,
        CrudRepository<HibernateAddress, Integer> {

    List<HibernateAddress> findAllByCode(String code);
}
