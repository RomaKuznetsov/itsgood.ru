package com.itsgood.ru.old.repository.impl;


import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.old.repository.AddressRepository;
import com.itsgood.ru.old.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

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
    public Address create(Address address) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.INSERT_ADDRESS.getCRUD());
            preparedStatement.setInt(1, address.getCustomer_id());
            preparedStatement.setString(2, address.getCode());
            preparedStatement.setString(3, address.getCountry());
            preparedStatement.setString(4, address.getRegion());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getStreet());
            preparedStatement.setInt(7, address.getHouse());
            preparedStatement.setString(8, address.getFrame());
            preparedStatement.setInt(9, address.getApartment());
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdAddress();
    }

    @Override
    public Address update(Address address) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.UPDATE_ADDRESS_ON_ID.getCRUD());
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getRegion());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setInt(5, address.getHouse());
            preparedStatement.setString(6, address.getFrame());
            preparedStatement.setInt(7, address.getApartment());
            preparedStatement.setInt(8, address.getId());
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(address.getId());
    }

    @Override
    public void delete(Integer id) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.DELETE_ADDRESS_ON_ID.getCRUD());
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
    public List<Address> findAll() {
        registerDriver();
        List listAllAddress;
        Address address;
        try {
            listAllAddress = new ArrayList<>();
            address = new Address();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_CRUD.SELECT_ALL_ADDRESS.getCRUD());
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
                listAllAddress.add(address);
            }
            return listAllAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Address findOne(Integer id) {
        registerDriver();
        Address address;
        try {
            address = new Address();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ADDRESS_ON_ID.getCRUD());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListAddressOneCustomer(Customer customer) {
        registerDriver();
        List listOneCustomerAddress;
        Address address;
        try {
            listOneCustomerAddress = new ArrayList<>();
            address = new Address();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ALL_ADDRESS_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
                listOneCustomerAddress.add(address);
            }
            return listOneCustomerAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListCustomerDelivery(Customer customer) {
        registerDriver();
        List<Address> listCustomerDelivery;
        Address address;
        try {
            listCustomerDelivery = new ArrayList<>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ADDRESS_DEL_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address = new Address();
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
                listCustomerDelivery.add(address);
            }
            return listCustomerDelivery;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findCustomerRegistration(Customer customer) {
        registerDriver();
        Address address;
        try {
            address = new Address();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ADDRESS_REG_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findMaxIdAddress() {
        registerDriver();
        Address address;
        try {
            address = new Address();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ADDRESS_ON_MAX_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findMinIdAddress() throws SQLException {
        registerDriver();
        Address address;
        try {
            address = new Address();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql_CRUD.SELECT_ADDRESS_ON_MIN_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setCustomer_id(resultSet.getInt(2));
                address.setCode(resultSet.getString(3));
                address.setCountry(resultSet.getString(4));
                address.setRegion(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setStreet(resultSet.getString(7));
                address.setHouse(resultSet.getInt(8));
                address.setFrame(resultSet.getString(9));
                address.setApartment(resultSet.getInt(10));
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
