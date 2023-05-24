package com.itsgood.ru.repository;

import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RoleDataRepository extends JpaRepository<RoleDTO, Integer>,
        PagingAndSortingRepository<RoleDTO, Integer>,
        CrudRepository<RoleDTO, Integer> {

    Optional<List<RoleDTO>> findRolesByCustomerAndValidityIsAfter(CustomerDTO customerDTO, Date validity);
}
