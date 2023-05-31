package com.itsgood.ru.repository.hibernate.impl;

import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.repository.hibernate.HibernateContractRepository;
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
    public ContractDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractDTO> findAll() throws SQLException {
        final String findAllHQL = "select u from ContractDTO u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, ContractDTO.class).getResultList();
    }

    @Override
    public ContractDTO create(ContractDTO contract) throws SQLException {
        return null;
    }

    @Override
    public ContractDTO update(ContractDTO contract) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
