package com.itsgood.ru.converters.impl.role;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.converters.RoleConverterRequestCreate;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.RoleDTO;
import com.itsgood.ru.exception.EntityNotFoundException;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestCreateImpl implements RoleConverterRequestCreate {
    private final CustomerDataRepository customerDataRepository;

    @Override
    public RoleDTO convert(RoleRequestCreate request) {
        RoleDTO roleDTO = new RoleDTO();
        Optional<CustomerDTO> searchCustomer = customerDataRepository.findById(request.getCustomer_id());
        CustomerDTO customerDTO = searchCustomer.orElseThrow(EntityNotFoundException::new);
        roleDTO.setCustomer(customerDTO);
        roleDTO.setRole(request.getRole());
        roleDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        roleDTO.setValidity(Date.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()).toLocalDate().plusYears(1)));
        return roleDTO;
}
}
