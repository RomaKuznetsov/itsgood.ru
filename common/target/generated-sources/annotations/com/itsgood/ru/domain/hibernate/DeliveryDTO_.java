package com.itsgood.ru.domain.hibernate;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveryDTO.class)
public abstract class DeliveryDTO_ {

	public static volatile SingularAttribute<DeliveryDTO, String> firstname;
	public static volatile SingularAttribute<DeliveryDTO, Timestamp> shipment_time;
	public static volatile SingularAttribute<DeliveryDTO, AddressDTO> address;
	public static volatile SetAttribute<DeliveryDTO, Contract_itemDTO> contracts_items;
	public static volatile SingularAttribute<DeliveryDTO, String> distance;
	public static volatile SingularAttribute<DeliveryDTO, Integer> phone;
	public static volatile SingularAttribute<DeliveryDTO, Integer> price;
	public static volatile SingularAttribute<DeliveryDTO, Timestamp> loading_time;
	public static volatile SingularAttribute<DeliveryDTO, Integer> id;
	public static volatile SingularAttribute<DeliveryDTO, Date> validity;
	public static volatile SingularAttribute<DeliveryDTO, String> lastname;
	public static volatile SingularAttribute<DeliveryDTO, Integer> stock_index;

	public static final String FIRSTNAME = "firstname";
	public static final String SHIPMENT_TIME = "shipment_time";
	public static final String ADDRESS = "address";
	public static final String CONTRACTS_ITEMS = "contracts_items";
	public static final String DISTANCE = "distance";
	public static final String PHONE = "phone";
	public static final String PRICE = "price";
	public static final String LOADING_TIME = "loading_time";
	public static final String ID = "id";
	public static final String VALIDITY = "validity";
	public static final String LASTNAME = "lastname";
	public static final String STOCK_INDEX = "stock_index";

}

