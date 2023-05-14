package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.HibernatePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDataRepository extends JpaRepository<HibernatePayment, Integer>,
        PagingAndSortingRepository<HibernatePayment, Integer>,
        CrudRepository<HibernatePayment, Integer> {

    Optional<HibernatePayment> findHibernatePaymentByCustomerAndStatus(HibernatePayment request);

    List<HibernatePayment> findHibernatePaymentByCustomerAndValidity(HibernatePayment request);
}

