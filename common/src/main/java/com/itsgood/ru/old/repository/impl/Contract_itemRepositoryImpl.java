package com.itsgood.ru.old.repository.impl;

import com.itsgood.ru.domain.Bucket;
import com.itsgood.ru.old.repository.Contract_itemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class Contract_itemRepositoryImpl implements Contract_itemRepository {

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
    public Bucket findMaxIdContract_Item() throws SQLException {
        return null;
    }

    @Override
    public Bucket findMinIdContract_Item() throws SQLException {
        return null;
    }

    @Override
    public Bucket createContract_itemCustomer(Bucket bucket) throws SQLException {
        return null;
    }


    @Override
    public Bucket findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Bucket> findAll() throws SQLException {
        return null;
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
