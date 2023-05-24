package com.itsgood.ru.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BucketDTO.class)
public abstract class BucketDTO_ {

	public static volatile SingularAttribute<BucketDTO, Timestamp> update_time;
	public static volatile SingularAttribute<BucketDTO, ItemDTO> item;
	public static volatile SingularAttribute<BucketDTO, Timestamp> create_time;
	public static volatile SingularAttribute<BucketDTO, ContractDTO> contract;
	public static volatile SingularAttribute<BucketDTO, EquipmentDTO> equipment;
	public static volatile SingularAttribute<BucketDTO, Integer> id;

	public static final String UPDATE_TIME = "update_time";
	public static final String ITEM = "item";
	public static final String CREATE_TIME = "create_time";
	public static final String CONTRACT = "contract";
	public static final String EQUIPMENT = "equipment";
	public static final String ID = "id";

}

