package com.itsgood.ru.old.repository;


import com.itsgood.ru.domain.Contract_item;

import java.sql.SQLException;


public interface Contract_itemRepository extends CRUDRepository<Integer, Contract_item> {
    Contract_item findMaxIdContract_Item() throws SQLException;
    Contract_item findMinIdContract_Item() throws SQLException;
    Contract_item createContract_itemCustomer(Contract_item contract_item) throws SQLException;
}
