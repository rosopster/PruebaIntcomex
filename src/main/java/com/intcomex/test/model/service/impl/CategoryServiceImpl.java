package com.intcomex.test.model.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.intcomex.test.common.constants.Constants;
import com.intcomex.test.common.exception.ItemNotFoundException;
import com.intcomex.test.model.dto.CategoryDTO;
import com.intcomex.test.model.entity.Category;
import com.intcomex.test.model.repository.CategoryRepository;
import com.intcomex.test.model.service.ICategoryService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Convert DTO to Entity
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category.setPicture(categoryDTO.getPicture());

        // Save entity in the database
        Category savedCategory = categoryRepository.save(category);

        // Convert Entity to DTO
        CategoryDTO savedCategoryDTO = new CategoryDTO();
        savedCategoryDTO.setId(savedCategory.getCategoryId());
        savedCategoryDTO.setCategoryName(savedCategory.getCategoryName());
        savedCategoryDTO.setDescription(savedCategory.getDescription());
        savedCategoryDTO.setPicture(savedCategory.getPicture());

        return savedCategoryDTO;
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
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND,
                    String.format(Constants.CATEGORY_NOT_FOUND_MESSAGE_ERROR, id));
        }
    }

    @Override
    public CategoryDTO createCategoryWithPhoto(String json, MultipartFile image) {

        CategoryDTO savedCategoryDTO = new CategoryDTO();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {

            if (image.isEmpty()) {
                throw new RuntimeException();
            }
            String filename = image.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            try (InputStream inputstream = image.getInputStream()) {
                Files.copy(inputstream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Falla al guardar archivo.");
            }

            node = mapper.readTree(json);
            String categoryName = node.get("categoryName").asText();
            String description = node.get("description").asText();
            // Convert DTO to Entity
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setDescription(description);
            category.setPicture(filename);

            // Save entity in the database
            Category savedCategory = categoryRepository.save(category);

            // Convert Entity to DTO
            savedCategoryDTO.setId(savedCategory.getCategoryId());
            savedCategoryDTO.setCategoryName(savedCategory.getCategoryName());
            savedCategoryDTO.setDescription(savedCategory.getDescription());
            savedCategoryDTO.setPicture(savedCategory.getPicture());

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return savedCategoryDTO;
    }
    
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer el archivo: " + filename);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("No se puede leer el archivo: " + filename);
        }
    }

}
