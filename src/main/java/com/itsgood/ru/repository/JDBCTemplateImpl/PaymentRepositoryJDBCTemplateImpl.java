package com.itsgood.ru.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Payment;
import com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper.PaymentRowMapper;
import com.itsgood.ru.repository.PaymentRepository;
import com.itsgood.ru.repositoryCRUD.Enums.SQL_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@RequiredArgsConstructor
public class PaymentRepositoryJDBCTemplateImpl implements PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PaymentRowMapper paymentRowMapper;

    @Override
    public List<Payment> findOneCustomer(Customer customer) {
        try {
            return jdbcTemplate.query(SQL_CRUD.SELECT_LIST_PAYMENT_ON_USERNAME_MAIL.getCRUD(),
                    paymentRowMapper, customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findOneCustomerActive(Customer customer) {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_ID_PAYMENT_ON_ACT_USERNAME_MAIL.getCRUD(),
                    paymentRowMapper, customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findMaxIdPayment() {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_PAYMENT_ON_MAX_ID.getCRUD(), paymentRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findMinIdPayment() {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_PAYMENT_ON_MIN_ID.getCRUD(), paymentRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_PAYMENT_ON_ID.getCRUD(), paymentRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(SQL_CRUD.SELECT_ALL_PAYMENT.getCRUD(), paymentRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment create(Payment payment) throws SQLException {
        jdbcTemplate.update(SQL_CRUD.INSERT_PAYMENT.getCRUD(), payment.getPhone(), payment.getStatus(),
                payment.getCustomer_id(), payment.getCard(), payment.getValidity());
        return findMaxIdPayment();
    }

    @Override
    public Payment customerCreatePayment(Map<String, Object> parameters) {
        try {
            Payment payment = (Payment) parameters.get("payment");
            Customer customer = (Customer) parameters.get("customer");
            jdbcTemplate.update(SQL_CRUD.INSERT_PAYMENT_ON_USERNAME_MAIL.getCRUD(), payment.getPhone(),
                    payment.getStatus(), customer.getUsername(), customer.getMail(), payment.getCard(),
                    payment.getValidity());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdPayment();
    }

    @Override
    public Payment update(Payment payment) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.UPDATE_PAYMENT_PHONE_LASTNAME_ON_ID.getCRUD(), payment.getStatus(),
                    payment.getPhone(), payment.getCard(), payment.getValidity(), payment.getId());
            return payment;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.DELETE_PAYMENT_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
