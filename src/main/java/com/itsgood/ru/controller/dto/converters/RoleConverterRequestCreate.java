package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import org.springframework.core.convert.converter.Converter;
public interface RoleConverterRequestCreate extends Converter<RoleRequestCreate, HibernateRole> {
}
