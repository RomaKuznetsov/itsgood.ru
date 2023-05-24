package com.itsgood.ru.repository;

import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ContractDataRepository extends JpaRepository<ContractDTO, Integer>,
        PagingAndSortingRepository<ContractDTO, Integer>,
        CrudRepository<ContractDTO, Integer> {

    Optional<ContractDTO> findContractByCustomer(CustomerDTO customer);
}
