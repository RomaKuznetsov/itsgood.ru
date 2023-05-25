package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.CategoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface HibernateCategoryService {

    CategoryDTO findOne(Integer id) throws SQLException;

    List<CategoryDTO> findAll() throws SQLException;

    CategoryDTO create(CategoryDTO contract) throws SQLException;

    CategoryDTO update(CategoryDTO contract) throws SQLException;

    void delete(Integer id) throws SQLException;
}
