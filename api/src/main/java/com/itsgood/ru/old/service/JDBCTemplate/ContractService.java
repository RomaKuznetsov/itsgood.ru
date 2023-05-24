package com.itsgood.ru.old.service.JDBCTemplate;

import com.itsgood.ru.old.domain.Contract;
import com.itsgood.ru.old.domain.Customer;

public interface ContractService {

    Contract serviceUpdateAddSum_contractCash(Customer customer);

    Contract serviceUpdateAddSum_contractCard(Customer customer);
}
