package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RoleDataRepository extends JpaRepository<RoleDTO, Integer>,
        PagingAndSortingRepository<RoleDTO, Integer>,
        CrudRepository<RoleDTO, Integer> {

    Optional<List<RoleDTO>> findHibernateRolesByCustomerAndValidityIsAfter(CustomerDTO customerDTO, Date validity);
}
