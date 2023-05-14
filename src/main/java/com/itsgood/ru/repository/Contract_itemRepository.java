package com.itsgood.ru.repository;


import com.itsgood.ru.domain.Contract_item;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;


public interface Contract_itemRepository extends CRUDRepository<Integer, Contract_item> {
    Contract_item findMaxIdContract_Item() throws SQLException;
    Contract_item findMinIdContract_Item() throws SQLException;
    Contract_item createContract_itemCustomer(Contract_item contract_item) throws SQLException;
}
