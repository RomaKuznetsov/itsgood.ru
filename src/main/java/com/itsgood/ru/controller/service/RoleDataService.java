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

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.*;

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
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerById(request.getCustomer_id());
        HibernateRole hibernateRole = roleConverterRequestCreate.convert(request);
        Set<HibernateRole> roles = hibernateCustomer.getRoles();
        if (!roles.contains(hibernateRole)) {
            hibernateRole.setCustomer(hibernateCustomer);
            hibernateRole = roleDataRepository.save(hibernateRole);
        }
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
        }
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
        }
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateRoleById(RoleRequestSearch request) {
        roleDataRepository.deleteById(request.getId());
        }

    public List<HibernateRole> findHibernateRoleByValidity(RoleRequestSearch request) {
        List<HibernateRole> searchAllRoles = findAllRoles();
        List<HibernateRole> searchAllValidityRoles = new ArrayList<>();
        Iterator<HibernateRole> roleIterator = searchAllRoles.listIterator();
        Date validityRequest = Date.valueOf(request.getValidity().toLocalDate().minusDays(3));
        while (roleIterator.hasNext()) {
            Date dateValidity = roleIterator.next().getValidity();
            if (dateValidity.after(validityRequest)) {
                searchAllValidityRoles.add(roleIterator.next());
            }
        }
        return searchAllValidityRoles;
    }
}


