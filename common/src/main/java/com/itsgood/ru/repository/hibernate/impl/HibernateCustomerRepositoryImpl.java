package com.itsgood.ru.repository.hibernate.impl;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.repository.hibernate.HibernateCustomerRepository;
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
    public CustomerDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<CustomerDTO> findAll() throws SQLException {
        final String findAllHQL = "select u from CustomerDTO u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, CustomerDTO.class).getResultList();

    }

    @Override
    public CustomerDTO create(CustomerDTO object) throws SQLException {
        Session session = sessionFactory.openSession();
        return update(object);
    }

    @Override
    public CustomerDTO update(CustomerDTO object) throws SQLException {
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
