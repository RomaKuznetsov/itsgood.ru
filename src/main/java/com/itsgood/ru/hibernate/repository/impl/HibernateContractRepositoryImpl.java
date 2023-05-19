package com.itsgood.ru.hibernate.repository.impl;



import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.repository.HibernateContractRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateContractRepositoryImpl implements HibernateContractRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public HibernateContract findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateContract> findAll() throws SQLException {
        final String findAllHQL = "select u from HibernateContract u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateContract.class).getResultList();
    }

    @Override
    public HibernateContract create(HibernateContract contract) throws SQLException {
        return null;
    }

    @Override
    public HibernateContract update(HibernateContract contract) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
