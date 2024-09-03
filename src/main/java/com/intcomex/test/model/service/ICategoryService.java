package com.intcomex.test.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.intcomex.test.model.dto.CategoryDTO;

public interface ICategoryService {
    
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(String json, MultipartFile image);
}
