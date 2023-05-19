package com.itsgood.ru.controller.rest.spring.data.repository;

import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestSearch;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryDataRepository extends JpaRepository<HibernateCategory, Integer>,
        PagingAndSortingRepository<HibernateCategory, Integer>,
        CrudRepository<HibernateCategory, Integer> {

    Optional<HibernateCategory> findHibernateCategoryByTitleOrId(String title, Integer id);
    Optional<HibernateCategory> findHibernateCategoryByTitle(String title);
    void deleteByTitleOrId(String title, Integer id);
}
