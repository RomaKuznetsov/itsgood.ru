package com.itsgood.ru.controller.dto.converters.impl.role;

import com.itsgood.ru.controller.dto.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class RoleConverterRequestUpdateImpl implements RoleConverterRequestUpdate {


    @Override
    public HibernateRole convert(RoleRequestUpdate request) {
        HibernateRole hibernateRole = new HibernateRole();
        hibernateRole.setId(request.getId());
        hibernateRole.setRole(request.getRole());
        hibernateRole.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateRole.setValidity(request.getValidity());
        return hibernateRole;
    }
}
