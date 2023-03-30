package com.muamerseljubac.mapper;

import com.muamerseljubac.entity.dtos.request.CategoryAddRequestDTO;
import com.muamerseljubac.entity.dtos.CategoryDTO;
import com.muamerseljubac.entity.dtos.request.CategoryEditRequestDTO;
import com.muamerseljubac.entity.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryAddRequestDTO requestDTO);

    @Mapping(source = "name", target = "name")
    CategoryDTO categoryToCategoryDto(Category category);

    Category categoryEditRequestDtoToCategory(CategoryEditRequestDTO requestDTO);
}
