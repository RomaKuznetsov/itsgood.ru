package com.itsgood.ru.controller.rest.spring.data.repository;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ContractDataRepository extends JpaRepository<HibernateContract, Integer>,
        PagingAndSortingRepository<HibernateContract, Integer>,
        CrudRepository<HibernateContract, Integer> {

    Optional<HibernateContract> findHibernateContractByCustomerAndRelevance(HibernateCustomer customer, String relevance);

    Optional<List<HibernateContract>> findAllHibernateContractsByCustomerAndRelevance(HibernateCustomer customer, String relevance);

}
