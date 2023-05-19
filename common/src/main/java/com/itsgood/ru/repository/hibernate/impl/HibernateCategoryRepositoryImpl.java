package com.itsgood.ru.repository.hibernate.impl;

import com.itsgood.ru.domain.hibernate.HibernateCategory;
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
    public HibernateCategory findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateCategory> findAll() throws SQLException {
        final String findAllHQL = "select u from HibernateCategory u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateCategory.class).getResultList();
    }

    @Override
    public HibernateCategory create(HibernateCategory object) throws SQLException {
        return null;
    }

    @Override
    public HibernateCategory update(HibernateCategory object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
