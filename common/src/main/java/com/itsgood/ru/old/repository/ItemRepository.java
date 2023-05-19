package com.itsgood.ru.old.repository;

import com.itsgood.ru.domain.Item;

import java.sql.SQLException;
import java.util.Map;

public interface ItemRepository extends CRUDRepository<Integer, Item> {
    Item findMaxIdItem() throws SQLException;
    Item findMinIdItem() throws SQLException;
    Item findItemTitleFirm(Item item) throws SQLException;
    Item createItemCategory(Map<String, Object> parameters) throws SQLException;
}
