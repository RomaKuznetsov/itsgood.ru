package com.itsgood.ru.repository.impl;

import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Payment;
import com.itsgood.ru.repository.PaymentRepository;
import com.itsgood.ru.sql.sql_CRUD;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

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
    public Payment create(Payment payment) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.INSERT_PAYMENT.getCRUD());
            preparedStatement.setInt(1, payment.getPhone());
            preparedStatement.setString(2, payment.getStatus());
            preparedStatement.setInt(3, payment.getCustomer_id());
            preparedStatement.setLong(4, Long.valueOf(payment.getCard()));
            preparedStatement.setDate(5, Date.valueOf(payment.getValidity()));
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findMaxIdPayment();
    }

    @Override
    public List<Payment> findAll() {
        registerDriver();
        List<Payment> listAllPayment;
        Payment payment;
        try {
            listAllPayment = new ArrayList<>();
            payment = new Payment();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_CRUD.SELECT_ALL_PAYMENT.getCRUD());
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
                listAllPayment.add(payment);
            }
            return listAllPayment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findOne(Integer id) {
        registerDriver();
        Payment payment;
        try {
            payment = new Payment();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_PAYMENT_ON_ID.getCRUD());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
            }
            return payment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.DELETE_PAYMENT_ON_ID.getCRUD());
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
    public Payment update(Payment payment) {
        registerDriver();
        int flag = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.UPDATE_PAYMENT_PHONE_LASTNAME_ON_ID.getCRUD());
            preparedStatement.setString(1, payment.getStatus());
            preparedStatement.setInt(2, payment.getPhone());
            preparedStatement.setString(3, payment.getCard());
            preparedStatement.setString(4, payment.getValidity());
            preparedStatement.setInt(5, payment.getId());
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(payment.getId());
    }

    @Override
    public List<Payment> findOneCustomer(Customer customer) {
        registerDriver();
        Payment payment;
        List<Payment> listAllPaymentId;
        try {
            listAllPaymentId = new ArrayList<>();
            payment = new Payment();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_LIST_PAYMENT_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
                listAllPaymentId.add(payment);
            }
            return listAllPaymentId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findOneCustomerActive(Customer customer) {
        registerDriver();
        Payment payment;
        try {
            payment = new Payment();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_ID_PAYMENT_ON_ACT_USERNAME_MAIL.getCRUD());
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
            }
            return payment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findMaxIdPayment() {
        registerDriver();
        Payment payment;
        try {
            payment = new Payment();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_PAYMENT_ON_MAX_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
            }
            return payment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findMinIdPayment() {
        registerDriver();
        Payment payment;
        try {
            payment = new Payment();
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.SELECT_PAYMENT_ON_MIN_ID.getCRUD());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setId(resultSet.getInt(1));
                payment.setStatus(resultSet.getString(2));
                payment.setCustomer_id(resultSet.getInt(3));
                payment.setPhone(resultSet.getInt(4));
                payment.setCard(resultSet.getString(5));
                payment.setValidity(resultSet.getString(6));
            }
            return payment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment customerCreatePayment(Map<String, Object> parameters) {
        registerDriver();
        Payment payment;
        Customer customer;
        int flag = 0;
        try {
            payment = (Payment) parameters.get("payment");
            customer = (Customer) parameters.get("customer");
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql_CRUD.INSERT_PAYMENT_ON_USERNAME_MAIL.getCRUD());
            preparedStatement.setInt(1, payment.getPhone());
            preparedStatement.setString(2, payment.getStatus());
            preparedStatement.setString(3, customer.getUsername());
            preparedStatement.setString(4, customer.getMail());
            preparedStatement.setLong(5, Long.valueOf(payment.getCard()));
            preparedStatement.setDate(6, Date.valueOf(payment.getValidity()));
            flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                connection.setAutoCommit(true);
            } else connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(payment.getId());
    }
}
