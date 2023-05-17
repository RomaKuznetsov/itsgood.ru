package com.itsgood.ru.controller.dto.converters.impl.role;

import com.itsgood.ru.controller.dto.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestUpdate;
import com.itsgood.ru.controller.service.RoleDataService;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.regex.qual.Regex;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestUpdateImpl implements RoleConverterRequestUpdate {

    private final RoleDataService roleDataService;

    @Override
    public HibernateRole convert(RoleRequestUpdate request) {
        HibernateRole hibernateRole = roleDataService.findHibernateRoleById(request.getId());
        hibernateRole.setRole(request.getRole());
        hibernateRole.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateRole.setValidity(request.getValidity());
        return hibernateRole;
    }
}
