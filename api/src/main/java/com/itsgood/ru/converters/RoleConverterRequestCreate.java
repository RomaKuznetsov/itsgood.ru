package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.domain.RoleDTO;
import org.springframework.core.convert.converter.Converter;
public interface RoleConverterRequestCreate extends Converter<RoleRequestCreate, RoleDTO> {
}
