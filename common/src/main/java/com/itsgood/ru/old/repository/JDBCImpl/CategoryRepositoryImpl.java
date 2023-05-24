package com.itsgood.ru.old.repository.JDBCImpl;

import com.itsgood.ru.old.domain.Category;
import com.itsgood.ru.old.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

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
    public Category findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return null;
    }

    @Override
    public Category create(Category object) throws SQLException {
        return null;
    }

    @Override
    public Category update(Category object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public Category findMaxIdCategory() throws SQLException {
        return null;
    }

    @Override
    public Category findMinIdCategory() throws SQLException {
        return null;
    }

    @Override
    public Category findCategoryOnTitle(Category category) throws SQLException {
        return null;
    }
}
