package com.intcomex.test.model.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.intcomex.test.common.constants.Constants;
import com.intcomex.test.common.exception.ItemNotFoundException;
import com.intcomex.test.model.dto.CategoryDTO;
import com.intcomex.test.model.entity.Category;
import com.intcomex.test.model.repository.CategoryRepository;
import com.intcomex.test.model.mapper.CategoryDtoMapper;
import com.intcomex.test.model.service.ICategoryService;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryDtoMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        // Convert entities to DTOs
        return categories.stream().map(category -> new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getPicture())).toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category foundCategory = category.get();
            return new CategoryDTO(
                    foundCategory.getCategoryId(),
                    foundCategory.getCategoryName(),
                    foundCategory.getDescription(),
                    foundCategory.getPicture());
        } else {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, String.format(Constants.CATEGORY_NOT_FOUND_MESSAGE_ERROR, id));
        }
    }

    @Override
    public CategoryDTO createCategory(String json, MultipartFile image) {

        Category savedCategory = new Category();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;

        try {
            node = mapper.readTree(json);
            String categoryName = node.get("categoryName").asText();
            String description = node.get("description").asText();
            
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setDescription(description);
            category.setPicture(image.getBytes());

            savedCategory = categoryRepository.save(category);

        } catch (JsonMappingException e) {

            System.out.println("Error " + e);
            //throw new ItemNotFoundException(HttpStatus., String.format(Constants.CATEGORY_NOT_FOUND_MESSAGE_ERROR, productRequest.getCategoryId()));

        } catch (JsonProcessingException e) {
            System.out.println("Error " + e);
            //throw new ItemNotFoundException(HttpStatus.NOT_FOUND, String.format(Constants.CATEGORY_NOT_FOUND_MESSAGE_ERROR, productRequest.getCategoryId()));
        } catch (IOException e){

            System.out.println("Error " + e);
        }

        return categoryMapper.categoryToDTO(savedCategory);
    }

}
