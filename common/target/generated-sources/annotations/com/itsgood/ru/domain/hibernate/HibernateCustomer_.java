package com.itsgood.ru.domain.hibernate;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateCustomer.class)
public abstract class HibernateCustomer_ {

	public static volatile SingularAttribute<HibernateCustomer, Date> birthday;
	public static volatile SingularAttribute<HibernateCustomer, String> firstname;
	public static volatile SingularAttribute<HibernateCustomer, AuthenticationInfo> authenticationInfo;
	public static volatile SetAttribute<HibernateCustomer, HibernateAddress> address;
	public static volatile SingularAttribute<HibernateCustomer, String> mail;
	public static volatile SingularAttribute<HibernateCustomer, String> gender;
	public static volatile SingularAttribute<HibernateCustomer, Timestamp> create_time;
	public static volatile SetAttribute<HibernateCustomer, HibernatePayment> payments;
	public static volatile SetAttribute<HibernateCustomer, HibernateRole> roles;
	public static volatile SetAttribute<HibernateCustomer, HibernateContract> contracts;
	public static volatile SingularAttribute<HibernateCustomer, String> lastname;
	public static volatile SingularAttribute<HibernateCustomer, Timestamp> update_time;
	public static volatile SingularAttribute<HibernateCustomer, Integer> phone;
	public static volatile SingularAttribute<HibernateCustomer, Integer> id;

	public static final String BIRTHDAY = "birthday";
	public static final String FIRSTNAME = "firstname";
	public static final String AUTHENTICATION_INFO = "authenticationInfo";
	public static final String ADDRESS = "address";
	public static final String MAIL = "mail";
	public static final String GENDER = "gender";
	public static final String CREATE_TIME = "create_time";
	public static final String PAYMENTS = "payments";
	public static final String ROLES = "roles";
	public static final String CONTRACTS = "contracts";
	public static final String LASTNAME = "lastname";
	public static final String UPDATE_TIME = "update_time";
	public static final String PHONE = "phone";
	public static final String ID = "id";

}

