package com.itsgood.ru.service.hibernate.impl;

import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
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
    public DeliveryDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<DeliveryDTO> findAll() throws SQLException {
        return  deliveryRepository.findAll();
    }

    @Override
    public DeliveryDTO create(DeliveryDTO delivery) throws SQLException {
        return null;
    }

    @Override
    public DeliveryDTO update(DeliveryDTO delivery) throws SQLException {
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
