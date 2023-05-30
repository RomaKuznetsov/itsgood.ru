package com.itsgood.ru.domain.hibernate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryDTO.class)
public abstract class CategoryDTO_ {

	public static volatile SingularAttribute<CategoryDTO, String> description;
	public static volatile SingularAttribute<CategoryDTO, Integer> id;
	public static volatile SingularAttribute<CategoryDTO, String> title;
	public static volatile SetAttribute<CategoryDTO, ItemDTO> items;

	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String ITEMS = "items";

}

