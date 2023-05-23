package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestCreate;
import com.itsgood.ru.controller.request.equipment.EquipmentRequestSearch;
import com.itsgood.ru.controller.request.equipment.EquipmentRequestUpdate;
import com.itsgood.ru.domain.hibernate.EquipmentDTO;
import com.itsgood.ru.service.spring.EquipmentDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/spring/equipment")
@RequiredArgsConstructor
public class EquipmentDataController {

    private final EquipmentDataService equipmentDataService;

    @GetMapping(value = "/findEquipmentById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<EquipmentDTO> findEquipmentById(@Validated @RequestBody EquipmentRequestSearch request,
                                                                  BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(equipmentDataService.findEquipmentById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllEquipment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<EquipmentDTO>> findAllEquipment() {
        return new ResponseEntity<>(equipmentDataService.findAllEquipment(), HttpStatus.OK);
    }

    @PostMapping(value = "/createEquipment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<EquipmentDTO> creatEquipment(@Validated @RequestBody EquipmentRequestCreate request,
                                                                BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(equipmentDataService.createEquipment(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/updateEquipment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<EquipmentDTO> updateEquipment(@Validated @RequestBody EquipmentRequestUpdate request,
                                                                BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(equipmentDataService.updateEquipment(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEquipment", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteEquipment(@Validated @RequestBody EquipmentRequestUpdate request,
                                                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        equipmentDataService.deleteEquipment(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEquipmentById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteEquipmentById(@Validated @RequestBody EquipmentRequestSearch request,
                                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        equipmentDataService.deleteEquipmentById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
