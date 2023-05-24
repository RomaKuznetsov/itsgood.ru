package com.itsgood.ru.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressDTO.class)
public abstract class AddressDTO_ {

	public static volatile SingularAttribute<AddressDTO, String> country;
	public static volatile SetAttribute<AddressDTO, EquipmentDTO> equipments;
	public static volatile SingularAttribute<AddressDTO, String> code;
	public static volatile SingularAttribute<AddressDTO, String> city;
	public static volatile SingularAttribute<AddressDTO, String> street;
	public static volatile SingularAttribute<AddressDTO, Integer> id;
	public static volatile SingularAttribute<AddressDTO, String> region;
	public static volatile SetAttribute<AddressDTO, ContractDTO> contracts;
	public static volatile SingularAttribute<AddressDTO, Integer> house;
	public static volatile SingularAttribute<AddressDTO, Integer> apartment;
	public static volatile SingularAttribute<AddressDTO, String> frame;
	public static volatile SingularAttribute<AddressDTO, CustomerDTO> customer;

	public static final String COUNTRY = "country";
	public static final String EQUIPMENTS = "equipments";
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

}

