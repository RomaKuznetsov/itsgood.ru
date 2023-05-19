package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;


import com.itsgood.ru.domain.Category;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.CategoryColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) {
        Category category;
        try {
            category = new Category();
            category.setId(rs.getInt(CategoryColumns.ID));
            category.setTitle(CategoryColumns.TITLE);
            category.setDescription(CategoryColumns.DESCRIPTION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
