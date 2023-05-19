package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.role.RoleRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateRole;
import org.springframework.core.convert.converter.Converter;

public interface RoleConverterRequestUpdate extends Converter<RoleRequestUpdate, HibernateRole> {
}
