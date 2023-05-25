package com.itsgood.ru.domain;

import java.math.BigInteger;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentDTO.class)
public abstract class PaymentDTO_ {

	public static volatile SingularAttribute<PaymentDTO, BigInteger> phone;
	public static volatile SingularAttribute<PaymentDTO, Integer> id;
	public static volatile SingularAttribute<PaymentDTO, Date> validity;
	public static volatile SetAttribute<PaymentDTO, ContractDTO> contracts;
	public static volatile SingularAttribute<PaymentDTO, BigInteger> card;
	public static volatile SingularAttribute<PaymentDTO, String> status;
	public static volatile SingularAttribute<PaymentDTO, CustomerDTO> customer;

	public static final String PHONE = "phone";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String CONTRACTS = "contracts";
	public static final String CARD = "card";
	public static final String STATUS = "status";
	public static final String CUSTOMER = "customer";

}

