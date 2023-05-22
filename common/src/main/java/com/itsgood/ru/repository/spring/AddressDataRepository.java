package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.AddressDTO;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface AddressDataRepository extends JpaRepository<AddressDTO, Integer>,
        PagingAndSortingRepository<AddressDTO, Integer>,
        CrudRepository<AddressDTO, Integer> {

    List<AddressDTO> findAllHibernateAddressByCode(String code);
    Optional<List<AddressDTO>> findAddressByCustomerAndCode(CustomerDTO customerDTO, String code);
}
