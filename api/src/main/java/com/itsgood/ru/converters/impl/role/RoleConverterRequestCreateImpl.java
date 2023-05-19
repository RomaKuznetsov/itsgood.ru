package com.itsgood.ru.converters.impl.role;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.converters.RoleConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import com.itsgood.ru.service.spring.CustomerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestCreateImpl implements RoleConverterRequestCreate {

    private final CustomerDataService customerDataService;

    @Override
    public RoleDTO convert(RoleRequestCreate request) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setCustomer(customerDataService.findHibernateCustomerById(request.getCustomer_id()));
        roleDTO.setRole(request.getRole());
        roleDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        roleDTO.setValidity(request.getValidity());
        return roleDTO;
    }
}
