package com.itsgood.ru.repository;

import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentDataRepository extends JpaRepository<PaymentDTO, Integer>,
        PagingAndSortingRepository<PaymentDTO, Integer>,
        CrudRepository<PaymentDTO, Integer> {

    Optional<PaymentDTO> findActivePaymentByCustomerAndStatus(CustomerDTO customer, String status);
    List<PaymentDTO> findInactivePaymentsByCustomerAndStatus(CustomerDTO customer, String status);
    List<PaymentDTO> findPaymentByCustomerAndValidity(CustomerDTO customer, Date validity);
    List<PaymentDTO> findPaymentDTOByPhone(int phone);

    List<PaymentDTO> findPaymentDTOByCard(Long card);
}

