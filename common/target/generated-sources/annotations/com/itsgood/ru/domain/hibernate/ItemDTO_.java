package com.itsgood.ru.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemDTO.class)
public abstract class ItemDTO_ {

	public static volatile SingularAttribute<ItemDTO, Timestamp> create_time;
	public static volatile SetAttribute<ItemDTO, BucketDTO> buckets;
	public static volatile SingularAttribute<ItemDTO, String> link;
	public static volatile SingularAttribute<ItemDTO, String> description;
	public static volatile SingularAttribute<ItemDTO, Integer> weight;
	public static volatile SingularAttribute<ItemDTO, String> title;
	public static volatile SetAttribute<ItemDTO, ContractDTO> contracts;
	public static volatile SingularAttribute<ItemDTO, String> volume;
	public static volatile SingularAttribute<ItemDTO, String> firm;
	public static volatile SingularAttribute<ItemDTO, Timestamp> update_time;
	public static volatile SingularAttribute<ItemDTO, Integer> price;
	public static volatile SingularAttribute<ItemDTO, Integer> id;
	public static volatile SingularAttribute<ItemDTO, CategoryDTO> category;

	public static final String CREATE_TIME = "create_time";
	public static final String BUCKETS = "buckets";
	public static final String LINK = "link";
	public static final String DESCRIPTION = "description";
	public static final String WEIGHT = "weight";
	public static final String TITLE = "title";
	public static final String CONTRACTS = "contracts";
	public static final String VOLUME = "volume";
	public static final String FIRM = "firm";
	public static final String UPDATE_TIME = "update_time";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String CATEGORY = "category";

}

