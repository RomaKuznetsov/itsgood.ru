package com.itsgood.ru.old.repository.impl;

import com.itsgood.ru.domain.Equipment;
import com.itsgood.ru.old.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

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
    public Equipment findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Equipment> findAll() throws SQLException {
        return null;
    }

    @Override
    public Equipment create(Equipment object) throws SQLException {
        return null;
    }

    @Override
    public Equipment update(Equipment object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public Equipment findMaxIdDelivery() throws SQLException {
        return null;
    }

    @Override
    public Equipment findMinIdDelivery() throws SQLException {
        return null;
    }

    @Override
    public Equipment createDeliveryCustomer(Map<String, Object> parameters) throws SQLException {
        return null;
    }
}
