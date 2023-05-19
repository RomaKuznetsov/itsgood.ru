package com.itsgood.ru.service;

import com.itsgood.ru.domain.Contract;
import com.itsgood.ru.domain.Customer;

public interface ContractService {

    Contract serviceUpdateAddSum_contractCash(Customer customer);

    Contract serviceUpdateAddSum_contractCard(Customer customer);
}
