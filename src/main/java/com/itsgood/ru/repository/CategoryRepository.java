package com.itsgood.ru.repository;

import com.itsgood.ru.domain.Category;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;

public interface CategoryRepository extends CRUDRepository<Integer, Category> {

    Category findMaxIdCategory() throws SQLException;
    Category findMinIdCategory() throws SQLException;
    Category findCategoryOnTitle(Category category) throws SQLException;
}
