package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.AddressDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeliveryDataRepository extends JpaRepository<DeliveryDTO, Integer>,
        PagingAndSortingRepository<DeliveryDTO, Integer>,
        CrudRepository<DeliveryDTO, Integer> {

    List<DeliveryDTO> findHibernateDeliveryByAddress(AddressDTO address);
}
