package com.itsgood.ru.old.repository.hibernate.impl;

import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.EquipmentDTO;
import com.itsgood.ru.old.repository.hibernate.HibernateDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateDeliveryRepositoryImpl implements HibernateDeliveryRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public EquipmentDTO findOne(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<EquipmentDTO> findAll() throws SQLException {
        final String findAllHQL = "select u from EquipmentDTO u";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, EquipmentDTO.class).getResultList();
    }

    @Override
    public EquipmentDTO create(EquipmentDTO object) throws SQLException {
        return null;
    }

    @Override
    public EquipmentDTO update(EquipmentDTO object) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public ContractDTO findDeliveryListItem() throws SQLException {


//  SELECT_ID_CONTRACT_ITEM_ADDRESS_ON_USERNAME_MAIL("SELECT Contract_Item.id, Contract.Address_id FROM Contract_Item " +
//                                                            "INNER JOIN Contract ON Contract.id = contract_id " +
//                                                            "INNER JOIN Customer ON Customer.id = customer_id " +
//                                                            "WHERE username = ? AND mail = ?;"),
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<HibernateCustomer> query_customer = cb.createQuery(HibernateCustomer.class);
//        Root<HibernateCustomer> root_customer = query_customer.from(HibernateCustomer.class); //Select * from
//
//        Expression<String> username = root_customer.get(HibernateCustomer_.username);
//        ParameterExpression<Integer> userSearchOnId = cb.parameter(HibernateCustomer_.id.getJavaType());
//
//
//        CriteriaQuery<HibernateContract> query_contract = cb.createQuery(HibernateContract.class);
//        Root<HibernateCustomer> root_contract = query_contract.from(HibernateCustomer.class); //Select * from
//
//
//
//        ParameterExpression<String> userSearchOnUsername = cb.parameter(HibernateCustomer_.username.getJavaType());
//
//        ParameterExpression<Integer> userSearchOnId = cb.parameter(HibernateCustomer_.id.getJavaType());
//        Expression<Integer> id = root_contract.get(HibernateCustomer_.id);



        return null;
    }
}
