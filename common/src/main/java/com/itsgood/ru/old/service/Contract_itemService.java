package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Bucket;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Item;

import java.sql.SQLException;

public interface Contract_itemService {

    Bucket serviceCreateContract_itemCash(Customer customer, Item item) throws SQLException;

    Bucket serviceCreateContract_itemCard(Customer customer, Item item) throws SQLException;
}
