package com.itsgood.ru.hibernate.service.impl;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import com.itsgood.ru.hibernate.repository.HibernateDeliveryRepository;
import com.itsgood.ru.hibernate.service.HibernateDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HibernateDeliveryServiceImpl implements HibernateDeliveryService {

    private final HibernateDeliveryRepository deliveryRepository;


    @Override
    public HibernateDelivery findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateDelivery> findAll() throws SQLException {
        return  deliveryRepository.findAll();
    }

    @Override
    public HibernateDelivery create(HibernateDelivery delivery) throws SQLException {
        return null;
    }

    @Override
    public HibernateDelivery update(HibernateDelivery delivery) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public HibernateContract findDeliveryListItem() throws SQLException {
        return null;
    }
}
