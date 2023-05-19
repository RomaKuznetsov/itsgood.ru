package com.itsgood.ru.service.hibernate.impl;


import com.itsgood.ru.domain.hibernate.HibernateItem;
import com.itsgood.ru.repository.hibernate.HibernateItemRepository;
import com.itsgood.ru.service.hibernate.HibernateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateItemServiceImpl implements HibernateItemService {

    private final HibernateItemRepository hibernateItemRepository;

    @Override
    public HibernateItem findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateItem> findAll() throws SQLException {
        return hibernateItemRepository.findAll();
    }

    @Override
    public HibernateItem create(HibernateItem object) throws SQLException {
        return hibernateItemRepository.create(object);
    }

    @Override
    public HibernateItem update(HibernateItem object) throws SQLException {
        return hibernateItemRepository.update(object);
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
