package com.itsgood.ru.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Category;
import com.itsgood.ru.domain.Item;
import com.itsgood.ru.repository.ItemRepository;
import com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper.ItemRowMapper;
import com.itsgood.ru.repositoryCRUD.Enums.SQL_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@RequiredArgsConstructor
public class ItemRepositoryJDBCTemplateImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ItemRowMapper itemRowMapper;

    @Override
    public Item findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_ITEM_ON_ID.getCRUD(), itemRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(SQL_CRUD.SELECT_ALL_ITEM.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item create(Item item) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.INSERT_ITEM.getCRUD(), item.getTitle(), item.getPrice(),
                    item.getFirm(), item.getLink(), item.getDescription(), item.getWeight(), item.getVolume(),
                    new Timestamp(System.currentTimeMillis()), item.getCategory_id());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } return findMaxIdItem();
    }

    @Override
    public Item update(Item item) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.UPDATE_ITEM_ON_ID.getCRUD(), item.getTitle(), item.getPrice(),
                    item.getFirm(), item.getLink(), item.getDescription(), item.getWeight(), item.getVolume(),
                    new Timestamp(System.currentTimeMillis()), item.getCategory_id());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findItemTitleFirm(item);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.DELETE_ITEM_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findMaxIdItem() {
        try {
           return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_ITEM_ON_MAX_ID.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findMinIdItem() throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_ITEM_ON_MIN_ID.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findItemTitleFirm(Item item) {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_ITEM_ON_TITLE_FIRM.getCRUD(), itemRowMapper,
                    item.getTitle(), item.getFirm());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item createItemCategory(Map<String, Object> parameters) {
        try {
            Item item = (Item) parameters.get("item");
            Category category = (Category) parameters.get("category");
            jdbcTemplate.update(SQL_CRUD.INSERT_ITEM_ON_CATEGORY_ID.getCRUD(), item.getTitle(), item.getPrice(),
                    item.getFirm(), item.getLink(), item.getDescription(), item.getWeight(), item.getVolume(),
                    new Timestamp(System.currentTimeMillis()), category.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdItem();
    }
}
