package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateContract_item.class)
public abstract class HibernateContract_item_ {

	public static volatile SingularAttribute<HibernateContract_item, HibernateDelivery> delivery;
	public static volatile SingularAttribute<HibernateContract_item, Timestamp> update_time;
	public static volatile SingularAttribute<HibernateContract_item, HibernateItem> item;
	public static volatile SingularAttribute<HibernateContract_item, Timestamp> create_time;
	public static volatile SingularAttribute<HibernateContract_item, HibernateContract> contract;
	public static volatile SingularAttribute<HibernateContract_item, Integer> id;

	public static final String DELIVERY = "delivery";
	public static final String UPDATE_TIME = "update_time";
	public static final String ITEM = "item";
	public static final String CREATE_TIME = "create_time";
	public static final String CONTRACT = "contract";
	public static final String ID = "id";

}

