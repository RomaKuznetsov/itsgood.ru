package com.itsgood.ru.repository.hibernate;


import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
import com.itsgood.ru.old.repository.CRUDRepository;

import java.sql.SQLException;

public interface HibernateDeliveryRepository extends CRUDRepository<Integer, DeliveryDTO> {

    ContractDTO findDeliveryListItem() throws SQLException;
}
