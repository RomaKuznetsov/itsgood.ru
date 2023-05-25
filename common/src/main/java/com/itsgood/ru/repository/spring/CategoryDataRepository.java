package com.itsgood.ru.repository.spring;


import com.itsgood.ru.domain.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryDataRepository extends JpaRepository<CategoryDTO, Integer>,
        PagingAndSortingRepository<CategoryDTO, Integer>,
        CrudRepository<CategoryDTO, Integer> {

    Optional<CategoryDTO> findCategoryByTitleOrId(String title, Integer id);
    Optional<CategoryDTO> findCategoryByTitle(String title);
    void deleteByTitleOrId(String title, Integer id);
}
