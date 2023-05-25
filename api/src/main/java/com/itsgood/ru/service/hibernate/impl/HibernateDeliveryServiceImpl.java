package com.itsgood.ru.service.hibernate.impl;

import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.EquipmentDTO;
import com.itsgood.ru.repository.hibernate.HibernateDeliveryRepository;
import com.itsgood.ru.service.hibernate.HibernateDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HibernateDeliveryServiceImpl implements HibernateDeliveryService {

    private final HibernateDeliveryRepository deliveryRepository;


    @Override
    public EquipmentDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<EquipmentDTO> findAll() throws SQLException {
        return  deliveryRepository.findAll();
    }

    @Override
    public EquipmentDTO create(EquipmentDTO delivery) throws SQLException {
        return null;
    }

    @Override
    public EquipmentDTO update(EquipmentDTO delivery) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public ContractDTO findDeliveryListItem() throws SQLException {
        return null;
    }
}
