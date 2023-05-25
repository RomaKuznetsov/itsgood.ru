package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.role.RoleRequestCreate;
import com.itsgood.ru.controller.request.role.RoleRequestSearch;
import com.itsgood.ru.controller.request.role.RoleRequestUpdate;
import com.itsgood.ru.domain.RoleDTO;
import com.itsgood.ru.service.spring.RoleDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/spring/role")
@RequiredArgsConstructor
public class RoleDataController {
    private final RoleDataService roleDataService;

    @PostMapping(value = "/createRole", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> createRole(@Validated @RequestBody RoleRequestCreate request,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.createRole(request), HttpStatus.OK);
    }

    @PatchMapping(value = "/updateRole", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> updateRole(@Validated @RequestBody RoleRequestUpdate request,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.updateRole(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findRoleById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<RoleDTO> findRoleById(@Validated @RequestBody RoleRequestSearch request,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.findRoleById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllRoles", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        return new ResponseEntity<>(roleDataService.findAllRoles(), HttpStatus.OK);
    }

    @GetMapping(value = "/findRolesByValidity", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<RoleDTO>> findRolesByValidity(@Validated @RequestBody RoleRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(roleDataService.findRoleByAuthenticateAndValidity(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteRolesById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteRolesById(@Validated @RequestBody RoleRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        roleDataService.deleteRoleById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteRoles", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteRoles(@Validated @RequestBody RoleRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        roleDataService.deleteRole(request);
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
