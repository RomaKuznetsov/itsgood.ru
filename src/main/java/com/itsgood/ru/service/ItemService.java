package com.itsgood.ru.service;

import com.itsgood.ru.domain.Category;
import com.itsgood.ru.domain.Item;

public interface ItemService {

    Item serviceCreateItem(Category category, Item item);
}
