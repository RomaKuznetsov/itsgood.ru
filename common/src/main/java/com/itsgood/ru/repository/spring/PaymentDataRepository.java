package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentDataRepository extends JpaRepository<PaymentDTO, Integer>,
        PagingAndSortingRepository<PaymentDTO, Integer>,
        CrudRepository<PaymentDTO, Integer> {

    Optional<List<PaymentDTO>> findPaymentByCustomerAndStatus(CustomerDTO customer, String status);
    Optional<List<PaymentDTO>> findPaymentByCustomerAndValidity(CustomerDTO customer, Date validity);
}

