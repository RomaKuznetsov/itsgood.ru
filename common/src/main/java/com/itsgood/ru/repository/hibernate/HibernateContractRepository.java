package com.itsgood.ru.repository.hibernate;

import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

public interface HibernateContractRepository extends CRUDRepository<Integer, ContractDTO> {
}
