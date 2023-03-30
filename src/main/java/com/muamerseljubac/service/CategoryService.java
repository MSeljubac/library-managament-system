package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.CategoryDTO;
import com.muamerseljubac.entity.dtos.request.CategoryAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.CategoryEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.CategoryDeleteResponseDTO;
import com.muamerseljubac.entity.models.Category;
import com.muamerseljubac.mapper.CategoryMapper;
import com.muamerseljubac.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO addCategory(CategoryAddRequestDTO requestDTO) {
        Category category = categoryMapper.categoryDtoToCategory(requestDTO);
        category.setId(UUID.randomUUID());
        categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDto(category);
    }

    public CategoryDTO getCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            return categoryMapper.categoryToCategoryDto(categoryRepository.findById(id).orElse(null));
        } else {
            throw new RuntimeException("Category not found!");
        }
    }

    public List<CategoryDTO> getAllCategories(int page, String sort) {
        try {
            return categoryRepository.findAll(PageRequest.of(page, 10, sort != null ? Sort.by(sort) : Sort.by("name")))
                    .stream()
                    .map(categoryMapper::categoryToCategoryDto)
                    .toList();
        } catch (PropertyReferenceException pre) {
            throw new RuntimeException("Sort parameter invalid!");
        }
    }

    public CategoryDTO editCategory(CategoryEditRequestDTO requestDTO) {
        if (categoryRepository.existsById(requestDTO.getId())) {
            Category category = categoryMapper.categoryEditRequestDtoToCategory(requestDTO);
            categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDto(category);
        } else {
            throw new RuntimeException("Category not found!");
        }
    }


    public CategoryDeleteResponseDTO deleteCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new CategoryDeleteResponseDTO("Category with ID " + id + " has been deleted successfully!");
        } else {
            throw new RuntimeException("Category not found!");
        }
    }
}
