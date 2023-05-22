package com.itsgood.ru.domain.hibernate;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerDTO.class)
public abstract class CustomerDTO_ {

	public static volatile SingularAttribute<CustomerDTO, Date> birthday;
	public static volatile SingularAttribute<CustomerDTO, String> firstname;
	public static volatile SingularAttribute<CustomerDTO, AuthenticationInfo> authenticationInfo;
	public static volatile SetAttribute<CustomerDTO, AddressDTO> address;
	public static volatile SingularAttribute<CustomerDTO, String> mail;
	public static volatile SingularAttribute<CustomerDTO, String> gender;
	public static volatile SingularAttribute<CustomerDTO, Timestamp> create_time;
	public static volatile SetAttribute<CustomerDTO, PaymentDTO> payments;
	public static volatile SetAttribute<CustomerDTO, RoleDTO> roles;
	public static volatile SingularAttribute<CustomerDTO, ContractDTO> contract;
	public static volatile SingularAttribute<CustomerDTO, String> lastname;
	public static volatile SingularAttribute<CustomerDTO, Timestamp> update_time;
	public static volatile SingularAttribute<CustomerDTO, Integer> phone;
	public static volatile SingularAttribute<CustomerDTO, Integer> id;

	public static final String BIRTHDAY = "birthday";
	public static final String FIRSTNAME = "firstname";
	public static final String AUTHENTICATION_INFO = "authenticationInfo";
	public static final String ADDRESS = "address";
	public static final String MAIL = "mail";
	public static final String GENDER = "gender";
	public static final String CREATE_TIME = "create_time";
	public static final String PAYMENTS = "payments";
	public static final String ROLES = "roles";
	public static final String CONTRACT = "contract";
	public static final String LASTNAME = "lastname";
	public static final String UPDATE_TIME = "update_time";
	public static final String PHONE = "phone";
	public static final String ID = "id";

}

