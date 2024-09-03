package com.intcomex.test.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.intcomex.test.model.dto.CategoryDTO;
import com.intcomex.test.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "picture", target = "picture")
    CategoryDTO categoryToDTO(Category category);

    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "picture", target = "picture")
    Category toCategory(CategoryDTO categoryDto);
}
