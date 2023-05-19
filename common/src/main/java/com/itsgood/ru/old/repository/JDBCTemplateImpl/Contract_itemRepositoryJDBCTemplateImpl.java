package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Contract_item;
import com.itsgood.ru.old.repository.Contract_itemRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.Contract_itemRowMapper;
import com.itsgood.ru.old.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class Contract_itemRepositoryJDBCTemplateImpl implements Contract_itemRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall simpleJdbcCall;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Contract_itemRowMapper contract_itemRowMapper;

    @Override
    public Contract_item createContract_itemCustomer(Contract_item contract_item) {
        try {
            simpleJdbcCall.withFunctionName("SUM_NUMBER_ITEM_ON_CONTRACT_ITEM_ID");
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("id_contract", contract_item.getContract_id())
                    .addValue("id_item", contract_item.getItem_id());
            simpleJdbcCall.executeFunction(Integer.class, in);
            return findOne(contract_item.getId());
        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract_item findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ITEM_ON_ID.getCRUD(),
                    contract_itemRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract_item> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_CONTRACT_ITEM.getCRUD(), contract_itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract_item create(Contract_item contract_item) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.INSERT_CONTRACT_ITEM.getCRUD(), contract_item.getContract_id(),
                    contract_item.getItem_id(), new Timestamp(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdContract_Item();
    }

    @Override
    public Contract_item update(Contract_item contract_item) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.UPDATE_CONTRACT_ITEM_ON_ID.getCRUD(), contract_item.getContract_id(),
                    contract_item.getItem_id(), new Timestamp(System.currentTimeMillis()));
            return contract_item;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.DELETE_CONTRACT_ITEM_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract_item findMaxIdContract_Item() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ITEM_ON_MAX_ID.getCRUD(),
                    contract_itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract_item findMinIdContract_Item() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ITEM_ON_MIN_ID.getCRUD(),
                    contract_itemRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
