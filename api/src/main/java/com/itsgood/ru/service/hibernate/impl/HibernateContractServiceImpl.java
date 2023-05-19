package com.itsgood.ru.service.hibernate.impl;


import com.itsgood.ru.domain.hibernate.HibernateContract;
import com.itsgood.ru.repository.hibernate.HibernateContractRepository;
import com.itsgood.ru.service.hibernate.HibernateContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateContractServiceImpl implements HibernateContractService {

    private final HibernateContractRepository hibernateContractRepository;


    @Override
    public HibernateContract findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateContract> findAll() throws SQLException {
        return hibernateContractRepository.findAll();
    }

    @Override
    public HibernateContract create(HibernateContract contract) throws SQLException {
        return null;
    }

    @Override
    public HibernateContract update(HibernateContract contract) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
