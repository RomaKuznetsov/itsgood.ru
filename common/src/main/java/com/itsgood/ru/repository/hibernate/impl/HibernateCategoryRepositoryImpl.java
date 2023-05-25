package com.itsgood.ru.repository.hibernate.impl;

import com.itsgood.ru.domain.CategoryDTO;
import com.itsgood.ru.repository.hibernate.HibernateCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HibernateCategoryRepositoryImpl implements HibernateCategoryRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;


    @Override
    public CategoryDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<CategoryDTO> findAll() throws SQLException {
        final String findAllHQL = "select u from CategoryDTO u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, CategoryDTO.class).getResultList();
    }

    @Override
    public CategoryDTO create(CategoryDTO object) throws SQLException {
        return null;
    }

    @Override
    public CategoryDTO update(CategoryDTO object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
