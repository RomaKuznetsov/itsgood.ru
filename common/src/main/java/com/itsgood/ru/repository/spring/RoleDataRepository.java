package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import com.itsgood.ru.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RoleDataRepository extends JpaRepository<HibernateRole, Integer>,
        PagingAndSortingRepository<HibernateRole, Integer>,
        CrudRepository<HibernateRole, Integer> {

    Optional<List<HibernateRole>> findHibernateRolesByCustomerAndValidityIsAfter(HibernateCustomer hibernateCustomer, Date validity);
}
