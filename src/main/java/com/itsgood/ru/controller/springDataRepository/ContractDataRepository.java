package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface ContractDataRepository extends JpaRepository<HibernateContract, Integer>,
        PagingAndSortingRepository<HibernateContract, Integer>,
        CrudRepository<HibernateContract, Integer> {

    Optional<HibernateContract> findHibernateContractByCustomerAndRelevance(HibernateContract hibernateContract);
}
