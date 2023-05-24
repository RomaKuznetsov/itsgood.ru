package com.itsgood.ru.old.service.JDBCTemplate.impl;

import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.CustomerRepository;
import com.itsgood.ru.old.service.JDBCTemplate.CustomerAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerAggregationServiceImpl implements CustomerAggregationService {

    private static Map<Integer, Customer> mapAllCustomer;

    private final CustomerRepository customerRepository;

    @Override
    public Map<Integer, Customer> createHashMapAllCustomer() {
        Customer customer;
        try {
            if (mapAllCustomer.size() == 0) {
                for (Customer cast : customerRepository.findAll().stream().collect(Collectors.toList())) {
                    mapAllCustomer.put(cast.hashCode(), cast);
                }
            }
            int countPKey = customerRepository.findMinIdCustomer().getId() + mapAllCustomer.size();
            int maxId = customerRepository.findMaxIdCustomer().getId();
            if (maxId > countPKey) {
                while (maxId > countPKey) {
                    customer = customerRepository.findOne(countPKey++);
                    if (customer != null) {
                        mapAllCustomer.put(customer.hashCode(), customer);
                    } else break;
                }
            }
            return mapAllCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Customer> createListAllCustomer() {
        List<Customer> listAllCustomers;
        try {
            listAllCustomers = customerRepository.findAll();
            if (listAllCustomers.size() == 0) {
                System.out.println("there are no customers on the list");
            }
            return listAllCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
