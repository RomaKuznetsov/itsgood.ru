package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import com.itsgood.ru.domain.hibernate.HibernatePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentDataRepository extends JpaRepository<HibernatePayment, Integer>,
        PagingAndSortingRepository<HibernatePayment, Integer>,
        CrudRepository<HibernatePayment, Integer> {

    Optional<List<HibernatePayment>> findHibernatePaymentByCustomerAndStatus(HibernateCustomer customer, String status);
    Optional<List<HibernatePayment>> findHibernatePaymentByCustomerAndValidity(HibernateCustomer customer, Date validity);
}

