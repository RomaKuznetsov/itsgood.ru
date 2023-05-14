package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.controller.dto.request.categoryDTO.CategoryRequestSearch;
import com.itsgood.ru.hibernate.domain.HibernateCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryDataRepository extends JpaRepository<HibernateCategory, Integer>,
        PagingAndSortingRepository<HibernateCategory, Integer>,
        CrudRepository<HibernateCategory, Integer> {

    Optional<HibernateCategory> findHibernateCategoryByTitleOrId(CategoryRequestSearch request);
    Optional<HibernateCategory> findHibernateCategoryByTitle(String title);
    void deleteByTitleOrId(HibernateCategory request);
}
