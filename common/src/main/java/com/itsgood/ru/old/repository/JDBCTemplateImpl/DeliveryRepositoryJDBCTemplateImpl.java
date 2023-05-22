package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Equipment;
import com.itsgood.ru.old.repository.DeliveryRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.DeliveryRowMapper;
import com.itsgood.ru.old.sql.sql_CRUD;
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
    public Equipment findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_DELIVERY_ON_ID.getCRUD(), deliveryRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Equipment> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_DELIVERY.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipment createDeliveryCustomer(Map<String, Object> parameters) throws SQLException {
        Customer customer;
        Equipment equipment;
        try {
            customer = (Customer) parameters.get("customer");
            equipment = (Equipment) parameters.get("delivery");
            Equipment parametersEquipment = findParametersForCreateDelivery(customer);
            jdbcTemplate.update(sql_CRUD.INSERT_DELIVERY.getCRUD(),
                    parametersEquipment.getAddress_id(), equipment.getFirstname(), equipment.getLastname(),
                    equipment.getPhone(), new Timestamp(System.currentTimeMillis()), equipment.getStock_index(),
                    equipment.getDistance(), equipment.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(5));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    public Equipment findParametersForCreateDelivery(Customer customer) {
        Equipment parametersEquipment;
        try {
            parametersEquipment = new Equipment();
            parametersEquipment = jdbcTemplate.query(sql_CRUD.SELECT_ID_CONTRACT_ITEM_ADDRESS_ON_USERNAME_MAIL.getCRUD(),
                    new ResultSetExtractor<Equipment>() {
                        @Override
                        public Equipment extractData(ResultSet rs) throws SQLException, DataAccessException {
                            Equipment parametersEquipment = new Equipment();
                            while (rs.next()) {
                                parametersEquipment = Equipment.builder().
                                        address_id(rs.getInt(2)).build();
                            }
                            return parametersEquipment;
                        }
                    }, customer.getUsername(), customer.getMail());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return parametersEquipment;
    }

    @Override
    public Equipment create(Equipment equipment) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.INSERT_DELIVERY.getCRUD(),
                    equipment.getAddress_id(), equipment.getFirstname(), equipment.getLastname(),
                    equipment.getPhone(), new Timestamp(System.currentTimeMillis()), equipment.getStock_index(),
                    equipment.getDistance(), equipment.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(5));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    @Override
    public Equipment update(Equipment equipment) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.UPDATE_DELIVERY_ON_ID.getCRUD(),
                    equipment.getAddress_id(), equipment.getFirstname(), equipment.getLastname(),
                    equipment.getPhone(), new Timestamp(System.currentTimeMillis()), equipment.getStock_index(),
                    equipment.getDistance(), equipment.getPrice(),
                    new Date(System.currentTimeMillis()).toLocalDate().plusDays(3));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdDelivery();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.DELETE_DELIVERY_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipment findMaxIdDelivery() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_DELIVERY_ON_MAX_ID.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipment findMinIdDelivery() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_DELIVERY_ON_MIN_ID.getCRUD(), deliveryRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
