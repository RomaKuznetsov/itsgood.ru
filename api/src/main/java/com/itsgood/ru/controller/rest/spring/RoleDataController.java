package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.controller.request.role.RoleRequestSearch;
import com.itsgood.ru.controller.request.role.RoleRequestUpdate;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import com.itsgood.ru.service.spring.RoleDataService;
import com.itsgood.ru.repository.spring.RoleDataRepository;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/springData/role")
@RequiredArgsConstructor
public class RoleDataController {
    private final RoleDataService roleDataService;

    private final RoleDataRepository roleDataRepository;

    @PostMapping(value = "/createHibernateRole", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> createHibernateRole(@Validated @RequestBody RoleRequestCreate request,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.createHibernateRole(request), HttpStatus.OK);
    }

    @PatchMapping(value = "/updateHibernateRole", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> updateHibernateRole(@Validated @RequestBody RoleRequestUpdate request,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.updateHibernateRole(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateRoleById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> findHibernateRoleById(@Validated @RequestBody RoleRequestSearch request,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.findHibernateRoleById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllRoles", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        return new ResponseEntity<>(roleDataService.findAllRoles(), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateRolesByValidity", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<RoleDTO>> findHibernateRolesByValidity(@Validated @RequestBody RoleRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.findHibernateRoleByAuthenticateAndValidity(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateRolesById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateRolesById(@Validated @RequestBody RoleRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        roleDataService.deleteHibernateRoleById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHibernateRoles", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateRoles(@Validated @RequestBody RoleRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        roleDataService.deleteHibernateRole(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Domain - Car
    //GET + /rest/cars - findAll
    //GET + /rest/cars/{id} - findOne
    //POST + /rest/cars - create object
    //PUT + /rest/cars - update object
    //DELETE + /rest/cars - update object

    //PATCH + /rest/cars  - partial update of object
    //GET + /rest/cars/search
    //GET + /rest/cars/search
    //PUT + /rest/cars/calculate
    //query - поисковой запрос
    //limit/offset = page = ограничение на число выводимых объектов
}
