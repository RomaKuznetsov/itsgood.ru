package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
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
