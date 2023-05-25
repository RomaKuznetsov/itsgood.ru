package com.itsgood.ru.service.hibernate.impl;


import com.itsgood.ru.domain.ItemDTO;
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
    public ItemDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> findAll() throws SQLException {
        return hibernateItemRepository.findAll();
    }

    @Override
    public ItemDTO create(ItemDTO object) throws SQLException {
        return hibernateItemRepository.create(object);
    }

    @Override
    public ItemDTO update(ItemDTO object) throws SQLException {
        return hibernateItemRepository.update(object);
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
