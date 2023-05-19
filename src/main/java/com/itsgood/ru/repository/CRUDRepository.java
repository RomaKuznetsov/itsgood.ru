package com.itsgood.ru.repository;

import java.sql.SQLException;
import java.util.List;

public interface CRUDRepository<K, T> {
    T findOne(K id) throws SQLException;

    List<T> findAll() throws SQLException;

    T create(T object) throws SQLException;

    T update(T object) throws SQLException;

    void delete(K id) throws SQLException;
}
