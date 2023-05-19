package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateRole;
import org.springframework.core.convert.converter.Converter;
public interface RoleConverterRequestCreate extends Converter<RoleRequestCreate, HibernateRole> {
}
