package com.itsgood.ru.JDBCTemplate.service.impl;

import com.itsgood.ru.old.domain.Contract;
import com.itsgood.ru.old.domain.Bucket;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.domain.Item;
import com.itsgood.ru.old.repository.ContractRepository;
import com.itsgood.ru.old.repository.Contract_itemRepository;
import com.itsgood.ru.old.repository.ItemRepository;
import com.itsgood.ru.JDBCTemplate.service.Contract_itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class Contract_itemServiceImpl implements Contract_itemService {

    private final ContractRepository contractRepositoryJDBCTemplateImpl;

    private final ItemRepository itemRepositoryJDBCTemplateImpl;

    private final Contract_itemRepository contract_itemRepositoryJDBCTemplateImpl;


    @Override
    public Bucket serviceCreateContract_itemCash(Customer customer, Item item) {
        try {
            Contract contract = contractRepositoryJDBCTemplateImpl.customerFindContractCash(customer);
            Item itemVol = itemRepositoryJDBCTemplateImpl.findItemTitleFirm(item);
            return contract_itemRepositoryJDBCTemplateImpl.createContract_itemCustomer(Bucket.builder().contract_id(contract.getId()).
                    item_id(itemVol.getId()).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bucket serviceCreateContract_itemCard(Customer customer, Item item) {
        try {
            Contract contract = contractRepositoryJDBCTemplateImpl.customerFindContractCard(customer);
            Item itemVol = itemRepositoryJDBCTemplateImpl.findItemTitleFirm(item);
            return contract_itemRepositoryJDBCTemplateImpl.createContract_itemCustomer(Bucket.builder().contract_id(contract.getId()).
                    item_id(itemVol.getId()).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
