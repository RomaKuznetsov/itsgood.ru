package com.itsgood.ru.old.repository;

import com.itsgood.ru.old.domain.Category;

import java.sql.SQLException;

public interface CategoryRepository extends CRUDRepository<Integer, Category> {

    Category findMaxIdCategory() throws SQLException;
    Category findMinIdCategory() throws SQLException;
    Category findCategoryOnTitle(Category category) throws SQLException;
}
