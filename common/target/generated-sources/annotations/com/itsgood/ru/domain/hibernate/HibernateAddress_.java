package com.itsgood.ru.domain.hibernate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateAddress.class)
public abstract class HibernateAddress_ {

	public static volatile SingularAttribute<HibernateAddress, String> country;
	public static volatile SingularAttribute<HibernateAddress, String> code;
	public static volatile SingularAttribute<HibernateAddress, String> city;
	public static volatile SingularAttribute<HibernateAddress, String> street;
	public static volatile SingularAttribute<HibernateAddress, Integer> id;
	public static volatile SingularAttribute<HibernateAddress, String> region;
	public static volatile SetAttribute<HibernateAddress, HibernateContract> contracts;
	public static volatile SingularAttribute<HibernateAddress, Integer> house;
	public static volatile SingularAttribute<HibernateAddress, Integer> apartment;
	public static volatile SingularAttribute<HibernateAddress, String> frame;
	public static volatile SingularAttribute<HibernateAddress, HibernateCustomer> customer;
	public static volatile SetAttribute<HibernateAddress, HibernateDelivery> deliveries;

	public static final String COUNTRY = "country";
	public static final String CODE = "code";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String ID = "id";
	public static final String REGION = "region";
	public static final String CONTRACTS = "contracts";
	public static final String HOUSE = "house";
	public static final String APARTMENT = "apartment";
	public static final String FRAME = "frame";
	public static final String CUSTOMER = "customer";
	public static final String DELIVERIES = "deliveries";

}

