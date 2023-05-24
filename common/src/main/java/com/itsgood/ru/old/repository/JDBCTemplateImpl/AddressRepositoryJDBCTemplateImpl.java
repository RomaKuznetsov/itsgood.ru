package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.old.domain.Address;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.AddressRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.AddressRowMapper;
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

public class AddressRepositoryJDBCTemplateImpl implements AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AddressRowMapper addressRowMapper;

    @Override
    public Address findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ADDRESS_ON_ID.getCRUD(), addressRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_ADDRESS.getCRUD(), addressRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address create(Address address) throws SQLException {
        int iterate;
        try {
            iterate = jdbcTemplate.update(sql_CRUD.INSERT_ADDRESS.getCRUD(), address.getCustomer_id(), address.getCode(),
                    address.getCountry(), address.getRegion(), address.getCity(), address.getStreet(),
                    address.getHouse(), address.getFrame(), address.getApartment());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } return findMaxIdAddress();
    }

    @Override
    public Address update(Address address) throws SQLException {
        int iterate;
        try {
            iterate = jdbcTemplate.update(sql_CRUD.UPDATE_ADDRESS_ON_ID.getCRUD(), address.getCountry(),
                    address.getRegion(), address.getCity(), address.getStreet(), address.getHouse(),
                    address.getFrame(), address.getApartment(), address.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } return findOne(address.getId());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        int iterate;
        try {
            iterate = jdbcTemplate.update(sql_CRUD.DELETE_ADDRESS_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Address findCustomerRegistration(Customer customer) {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ADDRESS_REG_ON_USERNAME_MAIL.getCRUD(), addressRowMapper,
                    customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListCustomerDelivery(Customer customer) {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ADDRESS_DEL_ON_USERNAME_MAIL.getCRUD(),
                    addressRowMapper, customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Address> findListAddressOneCustomer(Customer customer) {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_ADDRESS_ON_USERNAME_MAIL.getCRUD(),
                    addressRowMapper, customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findMaxIdAddress() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ADDRESS_ON_MAX_ID.getCRUD(), addressRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findMinIdAddress() throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_ADDRESS_ON_MIN_ID.getCRUD(), addressRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
