package com.itsgood.ru.old.service.impl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Equipment;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.CustomerRepositoryJDBCTemplateImpl;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.DeliveryRepositoryJDBCTemplateImpl;
import com.itsgood.ru.old.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final CustomerRepositoryJDBCTemplateImpl customerRepositoryJDBCTemplateImpl;

    private final DeliveryRepositoryJDBCTemplateImpl deliveryRepositoryJDBCTemplate;

    @Override
    public Equipment createDeliveryCustomerParameters(Customer customer, Equipment equipment) {
        Map<String, Object> parameters;
        try {
            parameters = new HashMap<>();
            parameters.put("customer", customerRepositoryJDBCTemplateImpl.findCustomerByUsernameMail(customer));
            parameters.put("delivery", equipment);
            deliveryRepositoryJDBCTemplate.createDeliveryCustomer(parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
