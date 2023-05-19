package com.itsgood.ru.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Role;
import com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper.RoleRowMapper;
import com.itsgood.ru.repository.RoleRepository;
import com.itsgood.ru.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class RoleRepositoryJDBCTemplateImpl implements RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RoleRowMapper roleRowMapper;

    @Override
    public List<Role> findOneCustomer(Integer id) {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_LIST_ROLE_ON_CUSTOMER_ID.getCRUD(), roleRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findMaxIdRole() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ROLE_ON_MAX_ID.getCRUD(), roleRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findMinIdRole() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ROLE_ON_MIN_ID.getCRUD(), roleRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Role findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ROLE_ON_ID.getCRUD(), roleRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_ROLE.getCRUD(), roleRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role create(Role role) throws SQLException {
        jdbcTemplate.update(sql_CRUD.INSERT_ROLE.getCRUD(), role.getRole(), role.getCustomer_id(),
                new Timestamp(System.currentTimeMillis()), new Date(System.currentTimeMillis()).
                        toLocalDate().plusYears(1));
        return findMaxIdRole();
    }

    @Override
    public Role update(Role role) throws SQLException {
        jdbcTemplate.update(sql_CRUD.UPDATE_ROLE_VALIDITY_ON_ID.getCRUD(), role.getRole(), role.getValidity(),
                new Timestamp(System.currentTimeMillis()));
        return findOne(role.getId());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        jdbcTemplate.update(sql_CRUD.DELETE_ROLE_ON_ID.getCRUD(), id);
    }
}
