package com.itsgood.ru.repository.impl;

import com.itsgood.ru.domain.Item;
import com.itsgood.ru.repository.ItemRepository;
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
public class ItemRepositoryImpl implements ItemRepository {

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

    // поиск по цене определенного товара ниже среднего выбор средней цены товара
    @Override
    public Item findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        return null;
    }

    @Override
    public Item create(Item object) throws SQLException {
        return null;
    }

    @Override
    public Item update(Item object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public Item findMaxIdItem() throws SQLException {
        return null;
    }

    @Override
    public Item findMinIdItem() throws SQLException {
        return null;
    }

    @Override
    public Item findItemTitleFirm(Item item) throws SQLException {
        return null;
    }

    @Override
    public Item createItemCategory(Map<String, Object> parameters) throws SQLException {
        return null;
    }
}
