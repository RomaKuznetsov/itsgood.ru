package com.itsgood.ru.JDBCTemplate.service;

import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.domain.Payment;

public interface PaymentService {

    Payment serviceCreatePayment(Customer customer, Payment payment);
}
