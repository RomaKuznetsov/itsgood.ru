package com.itsgood.ru.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repository.CustomerRepository;
import com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper.CustomerRowMapper;
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

@Repository
@Primary
@RequiredArgsConstructor
public class CustomerRepositoryJDBCTemplateImpl implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    @Override
    public Customer findCustomerByMail(String mail) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_MAIL.getCRUD(),
                    customerRowMapper, mail);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerByUsernameMail(Customer customer) {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_USERNAME_MAIL.getCRUD(),
                    customerRowMapper, customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Customer findCustomerByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_USERNAME.getCRUD(),
                    customerRowMapper, username);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_ID.getCRUD(), customerRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(SQL_CRUD.SELECT_ALL_CUSTOMER.getCRUD(), customerRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findMaxIdCustomer() {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_MAX_ID.getCRUD(), customerRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findMinIdCustomer() throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_CUSTOMER_ON_MIN_ID.getCRUD(), customerRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer create(Customer customer) throws SQLException {
        int iterate;
        try {
                iterate = jdbcTemplate.update(SQL_CRUD.INSERT_CUSTOMER.getCRUD(), customer.getFirstname(),
                        customer.getLastname(), customer.getUsername(), customer.getMail(), customer.getPassword(),
                        customer.getPhone(), customer.getBirthday(), customer.getGender(),
                        new Timestamp(System.currentTimeMillis()));
            } catch(DataAccessException e) {
                throw new RuntimeException(e);
            }
        return findMaxIdCustomer();
    }

    @Override
    public Customer update(Customer customer) throws SQLException {
        int iterate;
        try {
            iterate = jdbcTemplate.update(SQL_CRUD.UPDATE_CUSTOMER_ON_ID.getCRUD(),
                    customer.getFirstname(), customer.getLastname(), customer.getUsername(), customer.getMail(),
                    customer.getPassword(), customer.getPhone(), customer.getBirthday(), customer.getGender(),
                    new Timestamp(System.currentTimeMillis()),
                    customer.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findOne(customer.getId());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.DELETE_CUSTOMER_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
