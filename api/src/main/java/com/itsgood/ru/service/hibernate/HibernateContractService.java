package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.ContractDTO;

import java.sql.SQLException;
import java.util.List;

public interface HibernateContractService {

    ContractDTO findOne(Integer id) throws SQLException;

    List<ContractDTO> findAll() throws SQLException;

    ContractDTO create(ContractDTO contract) throws SQLException;

    ContractDTO update(ContractDTO contract) throws SQLException;

    void delete(Integer id) throws SQLException;
}
