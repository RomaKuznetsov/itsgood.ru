package com.itsgood.ru.controller.rest;

import com.itsgood.ru.controller.request.category.CategoryRequestCreate;
import com.itsgood.ru.controller.request.category.CategoryRequestSearch;
import com.itsgood.ru.controller.request.category.CategoryRequestUpdate;
import com.itsgood.ru.domain.CategoryDTO;
import com.itsgood.ru.domain.ItemDTO;
import com.itsgood.ru.service.CategoryDataService;
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
@RequestMapping("/rest/spring/category")
@RequiredArgsConstructor
public class CategoryDataController {

    private final CategoryDataService categoryDataService;

    @GetMapping(value = "/findCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findCategoryById(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findCategoryById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findCategoryByTitle", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findCategoryByTitle(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findCategoryByTitle(request.getTitle()), HttpStatus.OK);
    }

    @GetMapping(value = "/findCategoryByTitleOrId", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> findCategoryByTitleOrId(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findCategoryByTitleOrId(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllCategories", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<CategoryDTO>> findAlleCategories() {
        return new ResponseEntity<>(categoryDataService.findAllCategories(), HttpStatus.OK);
    }

    @PostMapping(value = "/createCategory", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> createCategory(@Validated @RequestBody CategoryRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.createCategory(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<CategoryDTO> updateCategoryById(@Validated @RequestBody CategoryRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.updateCategoryById(request), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteCategoryByTitleOrId", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCategoryByTitleOrId(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        categoryDataService.deleteCategoryByTitleOrId(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteCategoryById(@Validated @RequestBody CategoryRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        categoryDataService.deleteCategoryById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findAllItemsCategoryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<ItemDTO>> findAllCategories(@Validated @RequestBody CategoryRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(categoryDataService.findAllItemsCategoryById(request.getId()), HttpStatus.OK);
    }
}
