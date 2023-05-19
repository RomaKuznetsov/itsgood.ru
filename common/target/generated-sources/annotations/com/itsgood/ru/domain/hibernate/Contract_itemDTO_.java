package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contract_itemDTO.class)
public abstract class Contract_itemDTO_ {

	public static volatile SingularAttribute<Contract_itemDTO, DeliveryDTO> delivery;
	public static volatile SingularAttribute<Contract_itemDTO, Timestamp> update_time;
	public static volatile SingularAttribute<Contract_itemDTO, ItemDTO> item;
	public static volatile SingularAttribute<Contract_itemDTO, Timestamp> create_time;
	public static volatile SingularAttribute<Contract_itemDTO, ContractDTO> contract;
	public static volatile SingularAttribute<Contract_itemDTO, Integer> id;

	public static final String DELIVERY = "delivery";
	public static final String UPDATE_TIME = "update_time";
	public static final String ITEM = "item";
	public static final String CREATE_TIME = "create_time";
	public static final String CONTRACT = "contract";
	public static final String ID = "id";

}

