package com.itsgood.ru.converters.impl.role;

import com.itsgood.ru.controller.request.role.RoleRequestUpdate;
import com.itsgood.ru.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.domain.RoleDTO;
import com.itsgood.ru.repository.spring.RoleDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleConverterRequestUpdateImpl implements RoleConverterRequestUpdate {
    private final RoleDataRepository roleDataRepository;
    @Override
    public RoleDTO convert(RoleRequestUpdate request) {
        Optional<RoleDTO> searchResult = roleDataRepository.findById(request.getId());
        RoleDTO roleDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        roleDTO.setRole(request.getRole());
        roleDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        roleDTO.setValidity(request.getValidity());
        return roleDTO;
    }
}
