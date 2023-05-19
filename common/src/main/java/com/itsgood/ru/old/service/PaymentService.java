package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Payment;

public interface PaymentService {

    Payment serviceCreatePayment(Customer customer, Payment payment);
}
