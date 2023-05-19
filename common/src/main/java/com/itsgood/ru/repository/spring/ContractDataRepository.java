package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ContractDataRepository extends JpaRepository<ContractDTO, Integer>,
        PagingAndSortingRepository<ContractDTO, Integer>,
        CrudRepository<ContractDTO, Integer> {

    Optional<ContractDTO> findHibernateContractByCustomerAndRelevance(CustomerDTO customer, String relevance);

    Optional<List<ContractDTO>> findAllHibernateContractsByCustomerAndRelevance(CustomerDTO customer, String relevance);

}
