package com.itsgood.ru.service.hibernate.impl;

import com.itsgood.ru.domain.hibernate.HibernateCategory;
import com.itsgood.ru.repository.hibernate.HibernateCategoryRepository;
import com.itsgood.ru.service.hibernate.HibernateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HibernateCategoryServiceImpl implements HibernateCategoryService {

   private final HibernateCategoryRepository hibernateCategoryRepository;

    @Override
    public HibernateCategory findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateCategory> findAll() throws SQLException {
        return hibernateCategoryRepository.findAll();
    }

    @Override
    public HibernateCategory create(HibernateCategory contract) throws SQLException {
        return null;
    }

    @Override
    public HibernateCategory update(HibernateCategory contract) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
