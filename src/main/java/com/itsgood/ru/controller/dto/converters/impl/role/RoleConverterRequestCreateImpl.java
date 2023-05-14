package com.itsgood.ru.controller.dto.converters.impl.role;

import com.itsgood.ru.controller.dto.converters.RoleConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestCreateImpl implements RoleConverterRequestCreate {

    @Override
    public HibernateRole convert(RoleRequestCreate request) {
        HibernateRole hibernateRole = new HibernateRole();
        hibernateRole.setRole(request.getRole());
        hibernateRole.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateRole.setValidity(request.getValidity());
        return hibernateRole;
    }
}
