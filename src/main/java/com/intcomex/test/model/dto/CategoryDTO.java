package com.intcomex.test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;  

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 15, message = "Category name must be between 2 and 15 characters")
    private String categoryName;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    private String picture;  
}
