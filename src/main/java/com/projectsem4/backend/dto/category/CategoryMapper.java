package com.projectsem4.backend.dto.category;

import com.projectsem4.backend.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //CategoryDto mappers
    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

    //CategoryDtoRes mappers
    Category categoryDtoResToCategory(CategoryDto categoryDto);
    CategoryDtoRes categoryToCategoryDtoRes(Category category);
    List<CategoryDtoRes> lsCategoryToCategoryDtoRes(List<Category> categories);
}
