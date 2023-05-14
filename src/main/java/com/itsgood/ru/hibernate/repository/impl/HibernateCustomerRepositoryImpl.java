package com.itsgood.ru.hibernate.repository.impl;

import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.repository.HibernateCustomerRepository;
import com.itsgood.ru.hibernate.service.HibernateCustomerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateCustomerRepositoryImpl implements HibernateCustomerRepository {
    private final SessionFactory sessionFactory;
    private final EntityManagerFactory entityManagerFactory;


    @Override
    public HibernateCustomer findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<HibernateCustomer> findAll() throws SQLException {
        final String findAllHQL = "select u from HibernateCustomer u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateCustomer.class).getResultList();
    }

    @Override
    public HibernateCustomer create(HibernateCustomer object) throws SQLException {
        return update(object);
    }

    @Override
    public HibernateCustomer update(HibernateCustomer object) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(object);
            session.flush();
            transaction.commit();
            return object;
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
