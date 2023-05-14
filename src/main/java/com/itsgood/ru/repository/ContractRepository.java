package com.itsgood.ru.repository;


import com.itsgood.ru.domain.Contract;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

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
