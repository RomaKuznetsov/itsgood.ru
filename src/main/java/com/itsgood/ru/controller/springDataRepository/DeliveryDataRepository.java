package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryDataRepository extends JpaRepository<HibernateDelivery, Integer>,
        PagingAndSortingRepository<HibernateDelivery, Integer>,
        CrudRepository<HibernateDelivery, Integer> {

    List<HibernateDelivery> findHibernateDeliveryByAddress(HibernateAddress address);
}
