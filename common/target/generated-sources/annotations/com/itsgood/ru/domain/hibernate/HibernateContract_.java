package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateContract.class)
public abstract class HibernateContract_ {

	public static volatile SingularAttribute<HibernateContract, Timestamp> update_time;
	public static volatile SingularAttribute<HibernateContract, HibernateAddress> address;
	public static volatile SetAttribute<HibernateContract, HibernateContract_item> contracts_items;
	public static volatile SingularAttribute<HibernateContract, Timestamp> create_time;
	public static volatile SingularAttribute<HibernateContract, Integer> sum_order;
	public static volatile SingularAttribute<HibernateContract, HibernatePayment> payment;
	public static volatile SingularAttribute<HibernateContract, Integer> id;
	public static volatile SingularAttribute<HibernateContract, String> payment_types;
	public static volatile ListAttribute<HibernateContract, HibernateItem> items;
	public static volatile SingularAttribute<HibernateContract, String> relevance;
	public static volatile SingularAttribute<HibernateContract, HibernateCustomer> customer;

	public static final String UPDATE_TIME = "update_time";
	public static final String ADDRESS = "address";
	public static final String CONTRACTS_ITEMS = "contracts_items";
	public static final String CREATE_TIME = "create_time";
	public static final String SUM_ORDER = "sum_order";
	public static final String PAYMENT = "payment";
	public static final String ID = "id";
	public static final String PAYMENT_TYPES = "payment_types";
	public static final String ITEMS = "items";
	public static final String RELEVANCE = "relevance";
	public static final String CUSTOMER = "customer";

}

