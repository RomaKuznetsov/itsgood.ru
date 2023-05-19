package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Category;
import com.itsgood.ru.old.repository.CategoryRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.CategoryRowMapper;
import com.itsgood.ru.old.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class CategoryRepositoryJDBCTemplateImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CategoryRowMapper categoryRowMapper;

    @Override
    public Category findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CATEGORY_ON_ID.getCRUD(), categoryRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_CATEGORY.getCRUD(), categoryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category create(Category category) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.INSERT_CATEGORY.getCRUD(), category.getTitle(), category.getDescription());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findCategoryOnTitle(category);
    }

    @Override
    public Category update(Category category) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.UPDATE_CATEGORY_ON_ID.getCRUD(), category.getTitle(), category.getDescription());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findCategoryOnTitle(category);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.DELETE_CATEGORY_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findMaxIdCategory() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CATEGORY_ON_MAX_ID.getCRUD(), categoryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findMinIdCategory() throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CATEGORY_ON_MIN_ID.getCRUD(), categoryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findCategoryOnTitle(Category category) {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CATEGORY_ON_TITLE.getCRUD(), categoryRowMapper,
                    category.getTitle());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
