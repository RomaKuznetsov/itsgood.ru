package com.itsgood.ru.service.hibernate;

import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;


import java.sql.SQLException;
import java.util.List;

public interface HibernateDeliveryService {
    DeliveryDTO findOne(Integer id) throws SQLException;

    List<DeliveryDTO> findAll() throws SQLException;

    DeliveryDTO create(DeliveryDTO delivery) throws SQLException;

    DeliveryDTO update(DeliveryDTO delivery) throws SQLException;

    void delete(Integer id) throws SQLException;

    ContractDTO findDeliveryListItem() throws SQLException;


}
