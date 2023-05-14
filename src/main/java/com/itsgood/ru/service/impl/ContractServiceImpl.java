package com.itsgood.ru.service.impl;

import com.itsgood.ru.domain.Contract;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repository.ContractRepository;
import com.itsgood.ru.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    @Qualifier
    private final ContractRepository contractRepositoryJDBCTemplateImpl;
    @Override
    public Contract serviceUpdateAddSum_contractCash(Customer customer) {
        try {
            return contractRepositoryJDBCTemplateImpl.UpdateAddSum_contract(contractRepositoryJDBCTemplateImpl
                    .customerFindContractCash(customer).getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contract serviceUpdateAddSum_contractCard(Customer customer) {
        try {
            return contractRepositoryJDBCTemplateImpl.UpdateAddSum_contract(contractRepositoryJDBCTemplateImpl
                    .customerFindContractCard(customer).getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
