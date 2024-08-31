package com.intcomex.test.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intcomex.test.model.dto.CategoryDTO;
import com.intcomex.test.model.service.ICategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
        CategoryDTO savedCategory = iCategoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PostMapping("/createCategoryWPhoto")
    public ResponseEntity<String> createCategory(@RequestParam("json") String json, @RequestParam("image") MultipartFile image) {

        iCategoryService.createCategoryWithPhoto(json, image);
        return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
        
    }

}
