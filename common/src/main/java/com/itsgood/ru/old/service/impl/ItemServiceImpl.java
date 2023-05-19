package com.itsgood.ru.old.service.impl;

import com.itsgood.ru.domain.Category;
import com.itsgood.ru.domain.Item;
import com.itsgood.ru.old.repository.CategoryRepository;
import com.itsgood.ru.old.repository.ItemRepository;
import com.itsgood.ru.old.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepositoryJDBCTemplate;

    private final CategoryRepository categoryRepositoryJDBCTemplate;


    public Item serviceCreateItems(Category category, Item item) {
        Map<String, Object> parameters;
        try {
            parameters = new HashMap<>();
            parameters.put("category", categoryRepositoryJDBCTemplate.findCategoryOnTitle(category));
            parameters.put("item", item);
            return itemRepositoryJDBCTemplate.createItemCategory(parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Item serviceCreateItem(Category category, Item item) {
      Category categoryVol;
        try {
            categoryVol = categoryRepositoryJDBCTemplate.findCategoryOnTitle(category);
            return itemRepositoryJDBCTemplate.create(Item.builder().title(item.getTitle()).price(item.getPrice()).
                    firm(item.getFirm()).link(item.getLink()).description(item.getDescription()).
                    weight(item.getWeight()).volume(item.getVolume()).category_id(categoryVol.getId()).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
