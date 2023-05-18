package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.RoleConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestCreate;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestSearch;
import com.itsgood.ru.controller.dto.request.roleDTO.RoleRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.RoleDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class RoleDataService {

    private final RoleDataRepository roleDataRepository;
    private final CustomerDataService customerDataService;
    private final RoleConverterRequestCreate roleConverterRequestCreate;
    private final RoleConverterRequestUpdate roleConverterRequestUpdate;


    public List<HibernateRole> findAllRoles() {
        return roleDataRepository.findAll();
    }

    public HibernateRole findHibernateRoleById(Integer id) {
        Optional<HibernateRole> searchResult = roleDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateRole createHibernateRole(RoleRequestCreate request) {
        HibernateRole hibernateRole = roleConverterRequestCreate.convert(request);
        if (!hibernateRole.getCustomer().getRoles().contains(hibernateRole)) {
            hibernateRole = roleDataRepository.save(hibernateRole);
        } else throw new EntityExistsException();
        return hibernateRole;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateRole updateHibernateRole(RoleRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerById(request.getCustomer_id());
        HibernateRole hibernateRole = roleConverterRequestUpdate.convert(request);
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        if (!roles.contains(hibernateRole)) {
            hibernateRole.setCustomer(hibernateCustomer);
            hibernateRole = roleDataRepository.save(hibernateRole);
        } else throw new EntityNotFoundException();
        return hibernateRole;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateRole(RoleRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerById(request.getCustomer_id());
        HibernateRole hibernateRole = roleConverterRequestUpdate.convert(request);
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        if (!roles.contains(hibernateRole)) {
            hibernateRole.setCustomer(hibernateCustomer);
            roleDataRepository.delete(hibernateRole);
        } else throw new EntityNotFoundException();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateRoleById(RoleRequestSearch request) {
        roleDataRepository.deleteById(request.getId());
    }

    public List<HibernateRole> findHibernateRoleByAuthenticateAndValidity(RoleRequestSearch request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernateRole>> searchResult = roleDataRepository.
                findHibernateRolesByCustomerAndValidityIsAfter(hibernateCustomer, request.getValidity());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}


