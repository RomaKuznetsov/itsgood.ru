package com.itsgood.ru.old.repository.JDBCTemplateImpl;

import com.itsgood.ru.old.domain.Contract;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.ContractRepository;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper.ContractRowMapper;
import com.itsgood.ru.old.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class ContractRepositoryJDBCTemplateImpl implements ContractRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall simpleJdbcCall;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ContractRowMapper contractRowMapper;

    @Override
    public Contract findMaxIdContract() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ON_MAX_ID.getCRUD(), contractRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract findMinIdContract() {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ON_MIN_ID.getCRUD(), contractRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Contract findParametersForCreateContract(Customer customer) {
        Contract parametersContract;
        try {
            parametersContract = new Contract();
            parametersContract = jdbcTemplate.query(sql_CRUD.SELECT_CUSTOMER_ADDRESS_PAYMENT.getCRUD(),
                    new ResultSetExtractor<Contract>() {
                        @Override
                        public Contract extractData(ResultSet rs) throws SQLException, DataAccessException {
                            Contract parametersContract = new Contract();
                            while (rs.next()) {
                                parametersContract = Contract.builder().customer_id(rs.getInt(1))
                                        .address_id(rs.getInt(2))
                                        .payment_id(rs.getInt(3)).build();
                            }
                            return parametersContract;
                        }
                    }, customer.getUsername(), customer.getMail());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return parametersContract;
    }

    @Override
    public Contract customerCreateContractCard(Customer customer) {
        Contract contract;
        try {
            contract = new Contract();
            contract = findParametersForCreateContract(customer);
            jdbcTemplate.update(sql_CRUD.INSERT_CONTRACT_CARD.getCRUD(), contract.getCustomer_id(),
                    contract.getAddress_id(), contract.getPayment_id(),
                    new Timestamp(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdContract();
    }

    @Override
    public Contract customerCreateContractCash(Customer customer) {
        Contract contract;
        try {
            contract = new Contract();
            contract = findParametersForCreateContract(customer);
            jdbcTemplate.update(sql_CRUD.INSERT_CONTRACT_CASH.getCRUD(), contract.getCustomer_id(),
                    contract.getAddress_id(), new Timestamp(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdContract();
    }

    @Override
    public Contract customerFindContractCash(Customer customer) {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ON_USERNAME_MAIL_CASH.getCRUD(),
                    contractRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract customerFindContractCard(Customer customer) {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ON_USERNAME_MAIL_CARD.getCRUD(),
                    contractRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract findOne(Integer id) throws SQLException {
        try {
            return jdbcTemplate.queryForObject(sql_CRUD.SELECT_CONTRACT_ON_ID.getCRUD(), contractRowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract> findAll() throws SQLException {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_CONTRACT.getCRUD(), contractRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract create(Contract contract) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.INSERT_CONTRACT.getCRUD(), contract.getCustomer_id(),
                    contract.getAddress_id(), contract.getPayment_id(), contract.getPayment_types(),
                    new Timestamp(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdContract();
    }

    @Override
    public List<Contract> findOneCustomer(Customer customer) {
        try {
            return jdbcTemplate.query(sql_CRUD.SELECT_ALL_CONTRACT_ON_USERNAME_MAIL.getCRUD(), contractRowMapper,
                    customer.getUsername(), customer.getMail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Contract update(Contract contract) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.UPDATE_CONTRACT_PAYMENT_TYPE_ON_ID.getCRUD(), contract.getId(),
                    contract.getPayment_types(), new Timestamp(System.currentTimeMillis()), contractRowMapper
            );
            return contract;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            jdbcTemplate.update(sql_CRUD.DELETE_CONTRACT_ON_ID.getCRUD(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract UpdateAddSum_contract(Integer id) {
        try {
            simpleJdbcCall.withFunctionName("SUM_NUMBER_ITEM_ON_CONTRACT_ID");
            SqlParameterSource in = new MapSqlParameterSource().addValue("id_contract", id);
            simpleJdbcCall.executeFunction(Integer.class, in);
            return findOne(id);
        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
