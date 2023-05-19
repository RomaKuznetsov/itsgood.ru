package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerDataRepository extends JpaRepository<HibernateCustomer, Integer>,
        PagingAndSortingRepository<HibernateCustomer, Integer>,
        CrudRepository<HibernateCustomer, Integer> {

    Optional<HibernateCustomer> findByMail(String mail);

    Optional<HibernateCustomer> findByAuthenticationInfoUsername(String username);

}
