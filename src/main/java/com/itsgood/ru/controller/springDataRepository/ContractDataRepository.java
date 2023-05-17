package com.itsgood.ru.controller.springDataRepository;

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

    Optional<HibernateContract> findHibernateContractByAuthenticateAndRelevance(HibernateCustomer customer, String relevance);

    Optional<List<HibernateContract>> findAllHibernateContractsByAuthenticateAndRelevance(HibernateCustomer customer, String relevance);

}
