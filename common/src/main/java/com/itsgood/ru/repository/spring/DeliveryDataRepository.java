package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateAddress;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeliveryDataRepository extends JpaRepository<HibernateDelivery, Integer>,
        PagingAndSortingRepository<HibernateDelivery, Integer>,
        CrudRepository<HibernateDelivery, Integer> {

    List<HibernateDelivery> findHibernateDeliveryByAddress(HibernateAddress address);
}
