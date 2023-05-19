package com.itsgood.ru.controller.rest.hibernate;

import com.itsgood.ru.domain.hibernate.HibernateCategory;
import com.itsgood.ru.service.hibernate.HibernateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/hibernate/category")
public class HibernateCategoryRest {

    private final HibernateCategoryService categoryService;

    @GetMapping(value = "/findAllCategories", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> findAllCategories() throws SQLException {
        List<HibernateCategory> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
