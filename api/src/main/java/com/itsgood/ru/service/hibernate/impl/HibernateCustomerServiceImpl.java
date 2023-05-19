package com.itsgood.ru.service.hibernate.impl;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
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
    public HibernateCustomer findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateCustomer> findAll() throws SQLException {
        return hibernateCustomerRepository.findAll();
    }

    @Override
    public HibernateCustomer create(HibernateCustomer object) throws SQLException {
        return null;
    }

    @Override
    public HibernateCustomer update(HibernateCustomer object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
