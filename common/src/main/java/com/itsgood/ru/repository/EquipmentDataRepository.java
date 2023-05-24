package com.itsgood.ru.repository;

import com.itsgood.ru.domain.AddressDTO;
import com.itsgood.ru.domain.EquipmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EquipmentDataRepository extends JpaRepository<EquipmentDTO, Integer>,
        PagingAndSortingRepository<EquipmentDTO, Integer>,
        CrudRepository<EquipmentDTO, Integer> {

    List<EquipmentDTO> findHibernateDeliveryByAddress(AddressDTO address);
}
