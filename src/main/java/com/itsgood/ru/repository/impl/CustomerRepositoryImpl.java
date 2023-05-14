package com.itsgood.ru.repository.impl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repository.CustomerRepository;
import com.itsgood.ru.repositoryCRUD.Enums.SQL_CRUD;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
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
    public Customer create(Customer customer) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CRUD.INSERT_CUSTOMER.getCRUD());
            preparedStatement.setString(1, customer.getFirstname());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getUsername());
            preparedStatement.setString(4, customer.getMail());
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.setInt(6, customer.getPhone());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(customer.getBirthday()));
            preparedStatement.setString(8, customer.getGender());
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdCustomer();
    }

    @Override
    public Customer update(Customer customer) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_CRUD.UPDATE_CUSTOMER_ON_ID.getCRUD());
            preparedStatement.setString(1, customer.getFirstname());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getUsername());
            preparedStatement.setString(4, customer.getMail());
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.setInt(6, customer.getPhone());
            preparedStatement.setString(7, customer.getBirthday());
            preparedStatement.setString(8, customer.getGender());
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(10, customer.getId());
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(customer.getId());
    }

    @Override
    public void delete(Integer id) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CRUD.DELETE_CUSTOMER_ON_ID.getCRUD());
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
    public List<Customer> findAll() {
        registerDriver();
        List<Customer> listAllCustomer;
        Customer customer;
        try {
            listAllCustomer = new ArrayList<>();
            customer = new Customer();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_CRUD.SELECT_ALL_CUSTOMER.getCRUD());
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstname(resultSet.getString(2));
                customer.setLastname(resultSet.getString(3));
                customer.setUsername(resultSet.getString(4));
                customer.setMail(resultSet.getString(5));
                customer.setPassword(resultSet.getString(6));
                customer.setPhone(resultSet.getInt(7));
                customer.setBirthday(resultSet.getString(8));
                customer.setGender(resultSet.getString(9));
                customer.setCreate_time(resultSet.getString(10));
                customer.setUpdate_time(resultSet.getString(11));
                listAllCustomer.add(customer);
            }
            return listAllCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findOne(Integer id) {
        registerDriver();
        Customer customer;
        try {
            customer = new Customer();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CRUD.SELECT_CUSTOMER_ON_ID.getCRUD());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstname(resultSet.getString(2));
                customer.setLastname(resultSet.getString(3));
                customer.setUsername(resultSet.getString(4));
                customer.setMail(resultSet.getString(5));
                customer.setPassword(resultSet.getString(6));
                customer.setPhone(resultSet.getInt(7));
                customer.setBirthday(resultSet.getString(8));
                customer.setGender(resultSet.getString(9));
                customer.setCreate_time(resultSet.getString(10));
                customer.setUpdate_time(resultSet.getString(11));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerByMail(String mail) throws SQLException {
        return null;
    }

    @Override
    public Customer findCustomerByUsernameMail(Customer customer) {
        registerDriver();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CRUD.SELECT_CUSTOMER_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstname(resultSet.getString(2));
                customer.setLastname(resultSet.getString(3));
                customer.setUsername(resultSet.getString(4));
                customer.setMail(resultSet.getString(5));
                customer.setPassword(resultSet.getString(6));
                customer.setPhone(resultSet.getInt(7));
                customer.setBirthday(resultSet.getString(8));
                customer.setGender(resultSet.getString(9));
                customer.setCreate_time(resultSet.getString(10));
                customer.setUpdate_time(resultSet.getString(11));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerByUsername(String username) throws SQLException {
        return null;
    }

    @Override
    public Customer findMaxIdCustomer() {
        registerDriver();
        Customer customer;
        try {
            customer = new Customer();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CRUD.SELECT_CUSTOMER_ON_MAX_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstname(resultSet.getString(2));
                customer.setLastname(resultSet.getString(3));
                customer.setUsername(resultSet.getString(4));
                customer.setMail(resultSet.getString(5));
                customer.setPassword(resultSet.getString(6));
                customer.setPhone(resultSet.getInt(7));
                customer.setBirthday(resultSet.getString(8));
                customer.setGender(resultSet.getString(9));
                customer.setCreate_time(resultSet.getString(10));
                customer.setUpdate_time(resultSet.getString(11));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findMinIdCustomer() {
        registerDriver();
        Customer customer;
        try {
            customer = new Customer();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CRUD.SELECT_CUSTOMER_ON_MIN_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstname(resultSet.getString(2));
                customer.setLastname(resultSet.getString(3));
                customer.setUsername(resultSet.getString(4));
                customer.setMail(resultSet.getString(5));
                customer.setPassword(resultSet.getString(6));
                customer.setPhone(resultSet.getInt(7));
                customer.setBirthday(resultSet.getString(8));
                customer.setGender(resultSet.getString(9));
                customer.setCreate_time(resultSet.getString(10));
                customer.setUpdate_time(resultSet.getString(11));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
