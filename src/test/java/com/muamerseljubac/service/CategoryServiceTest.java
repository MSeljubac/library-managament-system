package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.CategoryDTO;
import com.muamerseljubac.entity.models.Category;
import com.muamerseljubac.mapper.CategoryMapper;
import com.muamerseljubac.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void getCategoryTest() throws Exception {
        UUID id = UUID.randomUUID();
        Category category = new Category(id, "Mystery");

        Mockito.when(categoryRepository.existsById(id)).thenReturn(true);
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        CategoryDTO categoryDTO = categoryService.getCategory(id);

        Assert.assertEquals(categoryDTO, categoryMapper.categoryToCategoryDto(category));
    }

    @Test
    public void getAllAuthorsTest() {
        UUID id1 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Category category1 = new Category(id1, "Romance");
        Category category2 = new Category(id2, "Mystery");
        Category category3 = new Category(id3, "Horror");

        Mockito.when(categoryRepository.findAll(PageRequest.of(0, 10, Sort.by("name"))))
                .thenReturn(new PageImpl<>(Arrays.asList(category1, category2, category3)));

        List<CategoryDTO> categories = categoryService.getAllCategories(0, "name");

        Assert.assertEquals(categories, Stream.of(category1, category2, category3)
                .map(categoryMapper::categoryToCategoryDto).toList());
    }

}
