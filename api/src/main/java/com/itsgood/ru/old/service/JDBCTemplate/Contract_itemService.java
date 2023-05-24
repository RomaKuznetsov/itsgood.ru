package com.itsgood.ru.old.service.JDBCTemplate;

import com.itsgood.ru.old.domain.Bucket;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.domain.Item;

import java.sql.SQLException;

public interface Contract_itemService {

    Bucket serviceCreateContract_itemCash(Customer customer, Item item) throws SQLException;

    Bucket serviceCreateContract_itemCard(Customer customer, Item item) throws SQLException;
}
