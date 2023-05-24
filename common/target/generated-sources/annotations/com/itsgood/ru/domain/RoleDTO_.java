package com.itsgood.ru.domain;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleDTO.class)
public abstract class RoleDTO_ {

	public static volatile SingularAttribute<RoleDTO, Timestamp> update_time;
	public static volatile SingularAttribute<RoleDTO, String> role;
	public static volatile SingularAttribute<RoleDTO, Timestamp> create_time;
	public static volatile SingularAttribute<RoleDTO, Integer> id;
	public static volatile SingularAttribute<RoleDTO, Date> validity;
	public static volatile SingularAttribute<RoleDTO, CustomerDTO> customer;

	public static final String UPDATE_TIME = "update_time";
	public static final String ROLE = "role";
	public static final String CREATE_TIME = "create_time";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String CUSTOMER = "customer";

}

