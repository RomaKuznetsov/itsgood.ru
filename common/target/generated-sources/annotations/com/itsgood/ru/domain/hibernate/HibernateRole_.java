package com.itsgood.ru.domain.hibernate;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateRole.class)
public abstract class HibernateRole_ {

	public static volatile SingularAttribute<HibernateRole, Timestamp> update_time;
	public static volatile SingularAttribute<HibernateRole, String> role;
	public static volatile SingularAttribute<HibernateRole, Timestamp> create_time;
	public static volatile SingularAttribute<HibernateRole, Integer> id;
	public static volatile SingularAttribute<HibernateRole, Date> validity;
	public static volatile SingularAttribute<HibernateRole, HibernateCustomer> customer;

	public static final String UPDATE_TIME = "update_time";
	public static final String ROLE = "role";
	public static final String CREATE_TIME = "create_time";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String CUSTOMER = "customer";

}

