package com.itsgood.ru.service;

import com.itsgood.ru.domain.Contract_item;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Item;

import java.sql.SQLException;

public interface Contract_itemService {

    Contract_item serviceCreateContract_itemCash(Customer customer, Item item) throws SQLException;

    Contract_item serviceCreateContract_itemCard(Customer customer, Item item) throws SQLException;
}
