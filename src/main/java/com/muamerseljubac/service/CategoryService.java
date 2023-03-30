package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.CategoryAddRequestDTO;
import com.muamerseljubac.entity.dtos.CategoryDTO;
import com.muamerseljubac.entity.dtos.request.CategoryEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.CategoryDeleteResponseDTO;
import com.muamerseljubac.entity.models.Category;
import com.muamerseljubac.mapper.CategoryMapper;
import com.muamerseljubac.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        return categoryMapper.categoryToCategoryDto(categoryRepository.findById(id).orElse(null));
    }

    public List<CategoryDTO> getAllCategories(int page) {
        return categoryRepository.findAll(PageRequest.of(page, 10))
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .toList();
    }

    public CategoryDTO editCategory(CategoryEditRequestDTO requestDTO) {
        Category category = categoryMapper.categoryEditRequestDtoToCategory(requestDTO);
        categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDto(category);
    }


    public CategoryDeleteResponseDTO deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
        return new CategoryDeleteResponseDTO("Category with ID " + id + " has been deleted successfully!");
    }
}
