package com.itsgood.ru.old.service.impl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Payment;
import com.itsgood.ru.old.repository.CustomerRepository;
import com.itsgood.ru.old.repository.PaymentRepository;
import com.itsgood.ru.old.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CustomerRepository customerRepositoryJDBCTemplateImpl;

    private final PaymentRepository paymentRepositoryJGDBCTemplateImpl;


    public Payment serviceCreatePayments(Customer customer, Payment payment) {
        Map<String, Object> parameters;
        try {
            parameters = new HashMap<>();
            parameters.put("customer", customerRepositoryJDBCTemplateImpl.findCustomerByUsernameMail(customer));
            parameters.put("payment", payment);
            return paymentRepositoryJGDBCTemplateImpl.customerCreatePayment(parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Payment serviceCreatePayment(Customer customer, Payment payment) {
        Customer customerVol;
        try {
            customerVol = customerRepositoryJDBCTemplateImpl.findCustomerByUsernameMail(customer);
            return paymentRepositoryJGDBCTemplateImpl.create(Payment.builder().phone(payment.getPhone()).
                    status(payment.getStatus()).customer_id(customerVol.getId()).card(payment.getCard()).
                    validity(payment.getValidity()).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
