package com.itsgood.ru.old.repository;


import com.itsgood.ru.old.domain.Contract;
import com.itsgood.ru.old.domain.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ContractRepository extends CRUDRepository<Integer, Contract> {
    List<Contract> findOneCustomer(Customer customer) throws SQLException;

    Contract findMaxIdContract() throws SQLException;
    Contract findMinIdContract() throws SQLException;
    Contract customerCreateContractCard(Customer customer) throws SQLException;

    Contract customerCreateContractCash(Customer customer) throws SQLException;

    Contract UpdateAddSum_contract(Integer id) throws SQLException;

    Contract customerFindContractCash(Customer customer) throws SQLException;

    Contract customerFindContractCard(Customer customer) throws SQLException;

}
