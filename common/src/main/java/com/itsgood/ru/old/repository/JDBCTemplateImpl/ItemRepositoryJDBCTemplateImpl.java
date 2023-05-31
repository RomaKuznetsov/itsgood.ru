package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.old.domain.Category;
import com.itsgood.ru.old.domain.Item;
import com.itsgood.ru.old.repository.ItemRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.ItemRowMapper;
import com.itsgood.ru.old.sql.sql_CRUD;
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
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ITEM_ON_ID.getCRUD(), itemRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_ITEM.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item create(Item item) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.INSERT_ITEM.getCRUD(), item.getTitle(), item.getPrice(),
                    item.getFirm(), item.getLink(), item.getDescription(), item.getWeight(), item.getVolume(),
                    new Timestamp(System.currentTimeMillis()), item.getCategory_id());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } return findMaxIdItem();
    }

    @Override
    public Item update(Item item) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.UPDATE_ITEM_ON_ID.getCRUD(), item.getTitle(), item.getPrice(),
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
            jdbcTemplate.update(sql_CRUD.DELETE_ITEM_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findMaxIdItem() {
        try {
           return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ITEM_ON_MAX_ID.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findMinIdItem() throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ITEM_ON_MIN_ID.getCRUD(), itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findItemTitleFirm(Item item) {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ITEM_ON_TITLE_FIRM.getCRUD(), itemRowMapper,
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
            jdbcTemplate.update(sql_CRUD.INSERT_ITEM_ON_CATEGORY_ID.getCRUD(), item.getTitle(), item.getPrice(),
                    item.getFirm(), item.getLink(), item.getDescription(), item.getWeight(), item.getVolume(),
                    new Timestamp(System.currentTimeMillis()), category.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdItem();
    }
}
