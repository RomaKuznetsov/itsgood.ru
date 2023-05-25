package com.itsgood.ru.JDBCTemplate.service;

import com.itsgood.ru.old.domain.Category;
import com.itsgood.ru.old.domain.Item;

public interface ItemService {

    Item serviceCreateItem(Category category, Item item);
}
