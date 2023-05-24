package com.itsgood.ru.old.repository;


import com.itsgood.ru.old.domain.Bucket;

import java.sql.SQLException;


public interface Contract_itemRepository extends CRUDRepository<Integer, Bucket> {
    Bucket findMaxIdContract_Item() throws SQLException;
    Bucket findMinIdContract_Item() throws SQLException;
    Bucket createContract_itemCustomer(Bucket bucket) throws SQLException;
}
