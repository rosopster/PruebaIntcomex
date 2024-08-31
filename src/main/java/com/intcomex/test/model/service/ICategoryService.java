package com.intcomex.test.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.intcomex.test.model.dto.CategoryDTO;

public interface ICategoryService {
    void init() throws IOException;
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategoryWithPhoto(String json, MultipartFile image);
    Resource loadAsResource(String filename);
}
