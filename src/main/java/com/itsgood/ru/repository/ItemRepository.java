package com.itsgood.ru.repository;

import com.itsgood.ru.domain.Item;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;
import java.util.Map;

public interface ItemRepository extends CRUDRepository<Integer, Item> {
    Item findMaxIdItem() throws SQLException;
    Item findMinIdItem() throws SQLException;
    Item findItemTitleFirm(Item item) throws SQLException;
    Item createItemCategory(Map<String, Object> parameters) throws SQLException;
}
