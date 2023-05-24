package com.itsgood.ru.old.service.JDBCTemplate;

import com.itsgood.ru.old.domain.Category;
import com.itsgood.ru.old.domain.Item;

public interface ItemService {

    Item serviceCreateItem(Category category, Item item);
}
