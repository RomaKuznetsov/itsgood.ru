package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.controller.request.category.CategoryRequestSearch;
import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.domain.hibernate.CategoryDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.service.spring.CategoryDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/springData/category")
@RequiredArgsConstructor
public class CategoryDataController {

    private final CategoryDataService categoryDataService;

    @GetMapping(value = "/findHibernateCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findHibernateCategoryById(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findHibernateCategoryById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCategoryByTitle", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findHibernateCategoryByTitle(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findHibernateCategoryByTitle(request.getTitle()), HttpStatus.OK);
    }

    @GetMapping(value = "/findHibernateCategoryByTitleOrId", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findHibernateCategoryByTitleOrId(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findHibernateCategoryByTitleOrId(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllHibernateCategories", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<CategoryDTO>> findAllHibernateCategories() {
        return new ResponseEntity<>(categoryDataService.findAllHibernateCategories(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernateCategory", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> createHibernateCategory(@Validated @RequestBody CategoryRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.createHibernateCategory(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateHibernateCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> updateHibernateCategoryById(@Validated @RequestBody CategoryRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.updateHibernateCategoryById(request), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteHibernateCategoryByTitleOrId", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCategoryByTitleOrId(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        categoryDataService.deleteHibernateCategoryByTitleOrId(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteHibernateCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteHibernateCategoryById(@Validated @RequestBody CategoryRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        categoryDataService.deleteHibernateCategoryById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findAllItemsHibernateCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ItemDTO>> findAllHibernateCategories(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findAllItemsHibernateCategoryById(request.getId()), HttpStatus.OK);
    }
}
