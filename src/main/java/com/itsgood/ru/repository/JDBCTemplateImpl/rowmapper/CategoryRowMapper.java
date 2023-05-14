package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;


import com.itsgood.ru.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CategoryColumns.ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CategoryColumns.TITLE;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.CategoryColumns.DESCRIPTION;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) {
        Category category;
        try {
            category = new Category();
            category.setId(rs.getInt(ID));
            category.setTitle(TITLE);
            category.setDescription(DESCRIPTION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
