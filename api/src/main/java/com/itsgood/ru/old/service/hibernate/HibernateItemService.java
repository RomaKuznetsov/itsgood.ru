package com.itsgood.ru.old.service.hibernate;

import com.itsgood.ru.domain.ItemDTO;

import java.sql.SQLException;
import java.util.List;


public interface HibernateItemService {

    ItemDTO findOne(Integer id) throws SQLException;

    List<ItemDTO> findAll() throws SQLException;

    ItemDTO create(ItemDTO object) throws SQLException;

    ItemDTO update(ItemDTO object) throws SQLException;

    void delete(Integer id) throws SQLException;
}
