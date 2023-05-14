package com.itsgood.ru.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Delivery;
import com.itsgood.ru.repository.DeliveryRepository;
import com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper.DeliveryRowMapper;
import com.itsgood.ru.repositoryCRUD.Enums.SQL_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@RequiredArgsConstructor
public class DeliveryRepositoryJDBCTemplateImpl implements DeliveryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final DeliveryRowMapper deliveryRowMapper;

    @Override
    public Delivery findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_DELIVERY_ON_ID.getCRUD(), deliveryRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Delivery> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(SQL_CRUD.SELECT_ALL_DELIVERY.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Delivery createDeliveryCustomer(Map<String, Object> parameters) throws SQLException {
        Customer customer;
        Delivery delivery;
        try {
            customer = (Customer) parameters.get("customer");
            delivery = (Delivery) parameters.get("delivery");
            Delivery parametersDelivery = findParametersForCreateDelivery(customer);
            jdbcTemplate.update(SQL_CRUD.INSERT_DELIVERY.getCRUD(),
                    parametersDelivery.getAddress_id(), delivery.getFirstname(), delivery.getLastname(),
                    delivery.getPhone(), new Timestamp(System.currentTimeMillis()), delivery.getStock_index(),
                    delivery.getDistance(), delivery.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(5));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    public Delivery findParametersForCreateDelivery(Customer customer) {
        Delivery parametersDelivery;
        try {
            parametersDelivery = new Delivery();
            parametersDelivery = jdbcTemplate.query(SQL_CRUD.SELECT_ID_CONTRACT_ITEM_ADDRESS_ON_USERNAME_MAIL.getCRUD(),
                    new ResultSetExtractor<Delivery>() {
                        @Override
                        public Delivery extractData(ResultSet rs) throws SQLException, DataAccessException {
                            Delivery parametersDelivery = new Delivery();
                            while (rs.next()) {
                                parametersDelivery = Delivery.builder().
                                        address_id(rs.getInt(2)).build();
                            }
                            return parametersDelivery;
                        }
                    }, customer.getUsername(), customer.getMail());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return parametersDelivery;
    }

    @Override
    public Delivery create(Delivery delivery) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.INSERT_DELIVERY.getCRUD(),
                    delivery.getAddress_id(), delivery.getFirstname(), delivery.getLastname(),
                    delivery.getPhone(), new Timestamp(System.currentTimeMillis()), delivery.getStock_index(),
                    delivery.getDistance(), delivery.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(5));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    @Override
    public Delivery update(Delivery delivery) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.UPDATE_DELIVERY_ON_ID.getCRUD(),
                    delivery.getAddress_id(), delivery.getFirstname(), delivery.getLastname(),
                    delivery.getPhone(), new Timestamp(System.currentTimeMillis()), delivery.getStock_index(),
                    delivery.getDistance(), delivery.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(3));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(SQL_CRUD.DELETE_DELIVERY_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Delivery findMaxIdDelivery() {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_DELIVERY_ON_MAX_ID.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Delivery findMinIdDelivery() {
        try {
            return jdbcTemplate.queryForObject(SQL_CRUD.SELECT_DELIVERY_ON_MIN_ID.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
