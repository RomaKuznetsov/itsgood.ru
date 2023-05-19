package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.hibernate.domain.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface RoleDataRepository extends JpaRepository<HibernateRole, Integer>,
        PagingAndSortingRepository<HibernateRole, Integer>,
        CrudRepository<HibernateRole, Integer> {
}
