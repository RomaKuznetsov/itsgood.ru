package com.itsgood.ru.converters.impl.role;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.converters.RoleConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateRole;
import com.itsgood.ru.service.spring.CustomerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestCreateImpl implements RoleConverterRequestCreate {

    private final CustomerDataService customerDataService;

    @Override
    public HibernateRole convert(RoleRequestCreate request) {
        HibernateRole hibernateRole = new HibernateRole();
        hibernateRole.setCustomer(customerDataService.findHibernateCustomerById(request.getCustomer_id()));
        hibernateRole.setRole(request.getRole());
        hibernateRole.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateRole.setValidity(request.getValidity());
        return hibernateRole;
    }
}
