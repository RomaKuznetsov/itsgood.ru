package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import org.springframework.core.convert.converter.Converter;

public interface RoleConverterRequestUpdate extends Converter<RoleRequestUpdate, HibernateRole> {
}
