package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.controller.request.role.RoleRequestSearch;
import com.itsgood.ru.controller.request.role.RoleRequestUpdate;
import com.itsgood.ru.converters.RoleConverterRequestCreate;
import com.itsgood.ru.converters.RoleConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import com.itsgood.ru.repository.spring.RoleDataRepository;
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


    public List<RoleDTO> findAllRoles() {
        return roleDataRepository.findAll();
    }

    public RoleDTO findRoleById(Integer id) {
        Optional<RoleDTO> searchResult = roleDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
//ok
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public RoleDTO createRole(RoleRequestCreate request) {
        RoleDTO roleDTO = roleConverterRequestCreate.convert(request);
        if (!roleDTO.getCustomer().getRoles().contains(roleDTO)) {
            roleDTO = roleDataRepository.save(roleDTO);
        } else throw new EntityExistsException("User has this role");
        return roleDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public RoleDTO updateRole(RoleRequestUpdate request) {
        return roleConverterRequestUpdate.convert(request);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteRole(RoleRequestUpdate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerById(request.getCustomer_id());
        RoleDTO roleDTO = roleConverterRequestUpdate.convert(request);
        Set<RoleDTO> roles = customerDTO.getRoles();
        if (roles.contains(roleDTO)) {
            roleDTO.setCustomer(customerDTO);
            roleDataRepository.delete(roleDTO);
        } else throw new EntityNotFoundException("User does not have this role");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteRoleById(RoleRequestSearch request) {
        roleDataRepository.deleteById(request.getId());
    }

    public List<RoleDTO> findRoleByAuthenticateAndValidity(RoleRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<List<RoleDTO>> searchResult = roleDataRepository.
                findRolesByCustomerAndValidityIsAfter(customerDTO, request.getValidity());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}


