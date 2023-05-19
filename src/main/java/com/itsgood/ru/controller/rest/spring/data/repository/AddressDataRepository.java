package com.itsgood.ru.controller.rest.spring.data.repository;

import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface AddressDataRepository extends JpaRepository<HibernateAddress, Integer>,
        PagingAndSortingRepository<HibernateAddress, Integer>,
        CrudRepository<HibernateAddress, Integer> {

    List<HibernateAddress> findAllHibernateAddressByCode(String code);
    Optional<List<HibernateAddress>> findHibernateAddressByCustomerAndCode(HibernateCustomer hibernateCustomer, String code);
}
