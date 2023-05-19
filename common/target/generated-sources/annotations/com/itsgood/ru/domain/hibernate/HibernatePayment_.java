package com.itsgood.ru.domain.hibernate;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernatePayment.class)
public abstract class HibernatePayment_ {

	public static volatile SingularAttribute<HibernatePayment, Integer> phone;
	public static volatile SingularAttribute<HibernatePayment, Integer> id;
	public static volatile SingularAttribute<HibernatePayment, Date> validity;
	public static volatile SetAttribute<HibernatePayment, HibernateContract> contracts;
	public static volatile SingularAttribute<HibernatePayment, String> card;
	public static volatile SingularAttribute<HibernatePayment, String> status;
	public static volatile SingularAttribute<HibernatePayment, HibernateCustomer> customer;

	public static final String PHONE = "phone";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String CONTRACTS = "contracts";
	public static final String CARD = "card";
	public static final String STATUS = "status";
	public static final String CUSTOMER = "customer";

}

