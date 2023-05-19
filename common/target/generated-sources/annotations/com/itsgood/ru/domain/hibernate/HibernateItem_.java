package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateItem.class)
public abstract class HibernateItem_ {

	public static volatile SetAttribute<HibernateItem, HibernateContract_item> contracts_items;
	public static volatile SingularAttribute<HibernateItem, Timestamp> create_time;
	public static volatile SingularAttribute<HibernateItem, String> link;
	public static volatile SingularAttribute<HibernateItem, String> description;
	public static volatile SingularAttribute<HibernateItem, Integer> weight;
	public static volatile SingularAttribute<HibernateItem, String> title;
	public static volatile SetAttribute<HibernateItem, HibernateContract> contracts;
	public static volatile SingularAttribute<HibernateItem, String> volume;
	public static volatile SingularAttribute<HibernateItem, String> firm;
	public static volatile SingularAttribute<HibernateItem, Timestamp> update_time;
	public static volatile SingularAttribute<HibernateItem, Integer> price;
	public static volatile SingularAttribute<HibernateItem, Integer> id;
	public static volatile SingularAttribute<HibernateItem, HibernateCategory> category;

	public static final String CONTRACTS_ITEMS = "contracts_items";
	public static final String CREATE_TIME = "create_time";
	public static final String LINK = "link";
	public static final String DESCRIPTION = "description";
	public static final String WEIGHT = "weight";
	public static final String TITLE = "title";
	public static final String CONTRACTS = "contracts";
	public static final String VOLUME = "volume";
	public static final String FIRM = "firm";
	public static final String UPDATE_TIME = "update_time";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String CATEGORY = "category";

}

