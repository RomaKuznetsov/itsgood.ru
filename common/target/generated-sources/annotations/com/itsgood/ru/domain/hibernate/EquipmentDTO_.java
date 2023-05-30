package com.itsgood.ru.domain.hibernate;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EquipmentDTO.class)
public abstract class EquipmentDTO_ {

	public static volatile SingularAttribute<EquipmentDTO, String> firstname;
	public static volatile SingularAttribute<EquipmentDTO, Timestamp> shipment_time;
	public static volatile SingularAttribute<EquipmentDTO, AddressDTO> address;
	public static volatile SingularAttribute<EquipmentDTO, String> distance;
	public static volatile SingularAttribute<EquipmentDTO, BigInteger> phone;
	public static volatile SingularAttribute<EquipmentDTO, Integer> price;
	public static volatile SingularAttribute<EquipmentDTO, Timestamp> loading_time;
	public static volatile SetAttribute<EquipmentDTO, BucketDTO> buckets;
	public static volatile SingularAttribute<EquipmentDTO, Integer> id;
	public static volatile SingularAttribute<EquipmentDTO, Date> validity;
	public static volatile SingularAttribute<EquipmentDTO, String> lastname;
	public static volatile SingularAttribute<EquipmentDTO, Integer> stock_index;

	public static final String FIRSTNAME = "firstname";
	public static final String SHIPMENT_TIME = "shipment_time";
	public static final String ADDRESS = "address";
	public static final String DISTANCE = "distance";
	public static final String PHONE = "phone";
	public static final String PRICE = "price";
	public static final String LOADING_TIME = "loading_time";
	public static final String BUCKETS = "buckets";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String LASTNAME = "lastname";
	public static final String STOCK_INDEX = "stock_index";

}

