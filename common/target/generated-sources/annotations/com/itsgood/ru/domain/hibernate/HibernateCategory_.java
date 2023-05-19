package com.itsgood.ru.domain.hibernate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateCategory.class)
public abstract class HibernateCategory_ {

	public static volatile SingularAttribute<HibernateCategory, String> description;
	public static volatile SingularAttribute<HibernateCategory, Integer> id;
	public static volatile SingularAttribute<HibernateCategory, String> title;
	public static volatile SetAttribute<HibernateCategory, HibernateItem> items;

	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String ITEMS = "items";

}

