package com.itsgood.ru.repository;

import com.itsgood.ru.domain.AddressDTO;
import com.itsgood.ru.domain.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AddressDataRepository extends JpaRepository<AddressDTO, Integer>,
        PagingAndSortingRepository<AddressDTO, Integer>,
        CrudRepository<AddressDTO, Integer> {

    List<AddressDTO> findAllHibernateAddressByCode(String code);
    List<AddressDTO> findAddressByCustomerAndCode(CustomerDTO customerDTO, String code);
}
