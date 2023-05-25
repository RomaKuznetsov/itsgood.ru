package com.itsgood.ru.JDBCTemplate.service.impl;

import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.CustomerRepository;
import com.itsgood.ru.JDBCTemplate.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;


    @Override
    public Customer create(Customer customer) {
        Customer customerCreate;
        try {
            customerCreate = customerRepository.create(customer);
            return customerCreate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomer(Customer customer) {
        Customer findCustomer;
        try {
            findCustomer = customerRepository.findCustomerByUsernameMail(customer);
            return findCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customer> findCustomerByUsername(String username) {
        Customer findCustomer;
        try {
            findCustomer = customerRepository.findCustomerByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(findCustomer);
    }

    @Override
    public Customer findCustomerByMail(String mail) throws SQLException {
        Customer findCustomer;
        try {
            findCustomer = customerRepository.findCustomerByMail(mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findCustomer;
    }

    @Override
    public Customer findOne(Integer id) {
        Customer customer;
        try {
            customer = customerRepository.findOne(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }


    @Override
    public Customer update(Customer customer) {
        Customer customerUpdate;
        try {
            customer.setId(findCustomer(customer).getId());
            customerUpdate = customerRepository.update(customer);
            return customerUpdate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            customerRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
