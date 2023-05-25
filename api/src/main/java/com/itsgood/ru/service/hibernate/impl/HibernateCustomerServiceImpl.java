package com.itsgood.ru.service.hibernate.impl;

import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.repository.hibernate.HibernateCustomerRepository;
import com.itsgood.ru.service.hibernate.HibernateCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateCustomerServiceImpl implements HibernateCustomerService {

    private final HibernateCustomerRepository hibernateCustomerRepository;


    @Override
    public CustomerDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<CustomerDTO> findAll() throws SQLException {
        return hibernateCustomerRepository.findAll();
    }

    @Override
    public CustomerDTO create(CustomerDTO object) throws SQLException {
        return null;
    }

    @Override
    public CustomerDTO update(CustomerDTO object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
