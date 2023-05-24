package com.itsgood.ru.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuthenticationInfo.class)
public abstract class AuthenticationInfo_ {

	public static volatile SingularAttribute<AuthenticationInfo, String> password;
	public static volatile SingularAttribute<AuthenticationInfo, String> username;

	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";

}

