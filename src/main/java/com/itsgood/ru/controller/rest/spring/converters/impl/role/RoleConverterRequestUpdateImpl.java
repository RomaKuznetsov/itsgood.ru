package com.itsgood.ru.controller.rest.spring.converters.impl.role;

import com.itsgood.ru.controller.rest.spring.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.repository.RoleDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateRole;
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
    public HibernateRole convert(RoleRequestUpdate request) {
        Optional<HibernateRole> searchResult = roleDataRepository.findById(request.getId());
        HibernateRole hibernateRole = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateRole.setRole(request.getRole());
        hibernateRole.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateRole.setValidity(request.getValidity());
        return hibernateRole;
    }
}
