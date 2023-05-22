package com.itsgood.ru.converters.impl.role;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.converters.RoleConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import com.itsgood.ru.repository.hibernate.exception.EntityNotFoundException;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.service.spring.CustomerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        if (searchCustomer.isPresent()) {
            roleDTO.setCustomer(searchCustomer.orElseThrow(EntityNotFoundException::new));
        }
        roleDTO.setRole(request.getRole());
        roleDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        roleDTO.setValidity(request.getValidity());
        return roleDTO;
    }
}
