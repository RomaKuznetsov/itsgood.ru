package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContractDTO.class)
public abstract class ContractDTO_ {

	public static volatile SingularAttribute<ContractDTO, Timestamp> update_time;
	public static volatile SingularAttribute<ContractDTO, AddressDTO> address;
	public static volatile SingularAttribute<ContractDTO, Timestamp> create_time;
	public static volatile SetAttribute<ContractDTO, BucketDTO> buckets;
	public static volatile SingularAttribute<ContractDTO, PaymentDTO> payment;
	public static volatile SingularAttribute<ContractDTO, Integer> id;
	public static volatile SingularAttribute<ContractDTO, String> payment_types;
	public static volatile ListAttribute<ContractDTO, ItemDTO> items;
	public static volatile SingularAttribute<ContractDTO, CustomerDTO> customer;

	public static final String UPDATE_TIME = "update_time";
	public static final String ADDRESS = "address";
	public static final String CREATE_TIME = "create_time";
	public static final String BUCKETS = "buckets";
	public static final String PAYMENT = "payment";
	public static final String ID = "id";
	public static final String PAYMENT_TYPES = "payment_types";
	public static final String ITEMS = "items";
	public static final String CUSTOMER = "customer";

}

