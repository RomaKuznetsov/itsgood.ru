package com.itsgood.ru.repository.hibernate.impl;

import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.repository.hibernate.HibernateItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateItemRepositoryImpl implements HibernateItemRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public ItemDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> findAll() throws SQLException {
        final String findAllHQL = "select u from ItemDTO u";
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery(findAllHQL, HibernateItem.class).getResultList();
//        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, ItemDTO.class).getResultList();
    }

    @Override
    public ItemDTO create(ItemDTO object) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.save(object); // во время сохранения происходит запись id в object
            return object;
        }
    }

    @Override
    public ItemDTO update(ItemDTO object) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
