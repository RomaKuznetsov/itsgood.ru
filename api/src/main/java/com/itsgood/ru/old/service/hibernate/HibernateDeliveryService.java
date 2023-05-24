package com.itsgood.ru.old.service.hibernate;

import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.EquipmentDTO;


import java.sql.SQLException;
import java.util.List;

public interface HibernateDeliveryService {
    EquipmentDTO findOne(Integer id) throws SQLException;

    List<EquipmentDTO> findAll() throws SQLException;

    EquipmentDTO create(EquipmentDTO delivery) throws SQLException;

    EquipmentDTO update(EquipmentDTO delivery) throws SQLException;

    void delete(Integer id) throws SQLException;

    ContractDTO findDeliveryListItem() throws SQLException;


}
