package com.itsgood.ru.old.repository.JDBCImpl;

import com.itsgood.ru.old.domain.Contract;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.ContractRepository;
import com.itsgood.ru.old.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepository {
    private final Logger logger = Logger.getLogger(AddressRepositoryImpl.class);

    private void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/itsgood_ru?useUnicode=" +
                            "true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "DownLoadMaster5781440");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract UpdateAddSum_contract(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Contract customerFindContractCash(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public Contract customerFindContractCard(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public Contract update(Contract contract) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.UPDATE_CONTRACT_PAYMENT_TYPE_ON_ID.getCRUD());
            preparedStatement.setString(1, contract.getPayment_types());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(contract.getId());
    }

    @Override
    public void delete(Integer id) {
        registerDriver();
        try {
            int flag = 0;
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.DELETE_CONTRACT_ON_ID.getCRUD());
            preparedStatement.setInt(1, id);
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract create(Contract contract) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql_CRUD.INSERT_CONTRACT.getCRUD());
            preparedStatement.setInt(1, contract.getCustomer_id());
            preparedStatement.setInt(2, contract.getAddress_id());
            preparedStatement.setInt(3, contract.getPayment_id());
            preparedStatement.setString(4, contract.getPayment_types());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdContract();
    }

    @Override
    public Contract findOne(Integer id) {
        registerDriver();
        Contract contract;
        try {
            contract = new Contract();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_CONTRACT_ON_ID.getCRUD());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contract.setId(resultSet.getInt(1));
                contract.setCustomer_id(resultSet.getInt(2));
                contract.setAddress_id(resultSet.getInt(3));
                contract.setPayment_id(resultSet.getInt(4));
                contract.setSum_order(resultSet.getInt(5));
                contract.setPayment_types(resultSet.getString(6));
                contract.setCreate_time(resultSet.getString(7));
                contract.setUpdate_time(resultSet.getString(8));
            }
            return contract;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract> findOneCustomer(Customer customer) {
        registerDriver();
        List<Contract> listContractOneCustomer;
        Contract contract;
        try {
            listContractOneCustomer = new ArrayList<>();
            contract = new Contract();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_ALL_CONTRACT_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(1, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contract.setId(resultSet.getInt(1));
                contract.setCustomer_id(resultSet.getInt(2));
                contract.setAddress_id(resultSet.getInt(3));
                contract.setPayment_id(resultSet.getInt(4));
                contract.setSum_order(resultSet.getInt(5));
                contract.setPayment_types(resultSet.getString(6));
                contract.setCreate_time(resultSet.getString(7));
                contract.setUpdate_time(resultSet.getString(8));
                listContractOneCustomer.add(contract);
            }
            return listContractOneCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract> findAll() {
        registerDriver();
        List<Contract> listAllContract;
        Contract contract;
        try {
            listAllContract = new ArrayList<>();
            contract = new Contract();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_CRUD.SELECT_ALL_CONTRACT.getCRUD());
            while (resultSet.next()) {
                contract.setId(resultSet.getInt(1));
                contract.setCustomer_id(resultSet.getInt(2));
                contract.setAddress_id(resultSet.getInt(3));
                contract.setPayment_id(resultSet.getInt(4));
                contract.setSum_order(resultSet.getInt(5));
                contract.setPayment_types(resultSet.getString(6));
                contract.setCreate_time(resultSet.getString(7));
                contract.setUpdate_time(resultSet.getString(8));
                listAllContract.add(contract);
            }
            return listAllContract;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract customerCreateContractCard(Customer customer) {
        registerDriver();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_CUSTOMER_ADDRESS_PAYMENT.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(sql_CRUD.INSERT_CONTRACT_CARD.getCRUD());
            while (resultSet.next()) {
                preparedStatement.setInt(1, resultSet.getInt(1));
                preparedStatement.setInt(2, resultSet.getInt(2));
                preparedStatement.setInt(3, resultSet.getInt(3));
            }
            resultSet.close();
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int flag = 0;
            connection.setAutoCommit(false);
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
            return findMaxIdContract();
        } catch (RuntimeException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract customerCreateContractCash(Customer customer) {
        registerDriver();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_CUSTOMER_ADDRESS_PAYMENT.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(sql_CRUD.INSERT_CONTRACT_CASH.getCRUD());
            while (resultSet.next()) {
                preparedStatement.setInt(1, resultSet.getInt(1));
                preparedStatement.setInt(2, resultSet.getInt(2));
            }
            resultSet.close();
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int flag = 0;
            connection.setAutoCommit(false);
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
            return findMaxIdContract();
        } catch (RuntimeException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract findMaxIdContract() {
        registerDriver();
        Contract contract;
        try {
            contract = new Contract();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_CRUD.SELECT_CONTRACT_ON_MAX_ID.getCRUD());
            while (resultSet.next()) {
                contract.setId(resultSet.getInt(1));
                contract.setCustomer_id(resultSet.getInt(2));
                contract.setAddress_id(resultSet.getInt(3));
                contract.setPayment_id(resultSet.getInt(4));
                contract.setSum_order(resultSet.getInt(5));
                contract.setPayment_types(resultSet.getString(6));
                contract.setCreate_time(resultSet.getString(7));
                contract.setUpdate_time(resultSet.getString(8));
            }
            return contract;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract findMinIdContract() {
        registerDriver();
        Contract contract;
        try {
            contract = new Contract();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_CRUD.SELECT_CONTRACT_ON_MIN_ID.getCRUD());
            while (resultSet.next()) {
                contract.setId(resultSet.getInt(1));
                contract.setCustomer_id(resultSet.getInt(2));
                contract.setAddress_id(resultSet.getInt(3));
                contract.setPayment_id(resultSet.getInt(4));
                contract.setSum_order(resultSet.getInt(5));
                contract.setPayment_types(resultSet.getString(6));
                contract.setCreate_time(resultSet.getString(7));
                contract.setUpdate_time(resultSet.getString(8));
            }
            return contract;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

