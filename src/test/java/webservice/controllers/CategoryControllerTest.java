package webservice.controllers;

import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webservice.dto.CategoryDTO;
import webservice.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;
    @Mock
    private Predicate predicate;
    @Mock
    private Pageable pageable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCategoriesReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final HttpStatus actualStatusCode = categoryController.getAllCategories(predicate, pageable).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void getAllCategoriesReturnsListOfCategories() {
        final List<CategoryDTO> list = new ArrayList<>();
        ResponseEntity response;

        when(categoryService.getAll(predicate, pageable)).thenReturn(list);

        response = categoryController.getAllCategories(predicate, pageable);

        Assertions.assertEquals(list, response.getBody());
    }

    @Test
    public void getAllCategoriesCallsCategoryServiceGetAll() {
        categoryController.getAllCategories(predicate, pageable);
        verify(categoryService).getAll(predicate, pageable);
    }

    @Test
    public void getAllChildrenReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final int parentId = 1;
        final HttpStatus actualStatusCode = categoryController.getAllChildren(parentId, predicate, pageable).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void getAllChildrenReturnsListOfCategories() {
        final List<CategoryDTO> list = new ArrayList<>();
        final int parentId = 1;
        ResponseEntity response;

        when(categoryService.getChildren(parentId, predicate, pageable)).thenReturn(list);

        response = categoryController.getAllChildren(parentId, predicate, pageable);

        Assertions.assertEquals(list, response.getBody());
    }

    @Test
    public void getAllChildrenCallsCategoryServiceGetAll() {
        final int parentId = 1;
        categoryController.getAllChildren(parentId, predicate, pageable);
        verify(categoryService).getChildren(parentId, predicate, pageable);
    }

    @Test
    public void getCategoryReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final int categoryId = 1;
        final HttpStatus actualStatusCode = categoryController.getCategory(categoryId).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void getCategoryReturnsCategoryDTO() {
        final CategoryDTO category = new CategoryDTO();
        final int categoryId = 1;
        final ResponseEntity response;

        when(categoryService.getCategory(categoryId)).thenReturn(category);

        response = categoryController.getCategory(categoryId);

        Assertions.assertEquals(category, response.getBody());
    }

    @Test
    public void getCategoryCallsCategoryServiceGetCategory() {
        final int categoryId = 1;
        categoryController.getCategory(categoryId);
        verify(categoryService).getCategory(categoryId);
    }

    @Test
    public void createCategoryReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final CategoryDTO category = new CategoryDTO();
        final HttpStatus actualStatusCode = categoryController.createCategory(category).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void createCategoryReturnsCategoryDTO() {
        final CategoryDTO category = new CategoryDTO();
        final ResponseEntity response;

        when(categoryService.createCategory(category)).thenReturn(category);

        response = categoryController.createCategory(category);

        Assertions.assertEquals(category, response.getBody());
    }

    @Test
    public void createCategoryCallsCategoryServiceGetCategory() {
        final CategoryDTO category = new CategoryDTO();
        categoryController.createCategory(category);
        verify(categoryService).createCategory(category);
    }

    @Test
    public void updateCategoryReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final CategoryDTO category = new CategoryDTO();
        final HttpStatus actualStatusCode = categoryController.updateCategory(category).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void updateCategoryReturnsCategoryDTO() {
        final CategoryDTO category = new CategoryDTO();
        final ResponseEntity response;

        when(categoryService.updateCategory(category)).thenReturn(category);

        response = categoryController.updateCategory(category);

        Assertions.assertEquals(category, response.getBody());
    }

    @Test
    public void updateCategoryCallsCategoryServiceUpdateCategory() {
        final CategoryDTO category = new CategoryDTO();
        categoryController.updateCategory(category);
        verify(categoryService).updateCategory(category);
    }

    @Test
    public void deleteCategoryReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final int categoryId = 1;
        final HttpStatus actualStatusCode = categoryController.deleteCategory(categoryId).getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void deleteCategoryReturnsCategoryDTO() {
        final int categoryId = 1;
        ;
        final ResponseEntity response;

        when(categoryService.deleteCategory(categoryId)).thenReturn(true);

        response = categoryController.deleteCategory(categoryId);

        Assertions.assertEquals(true, response.getBody());
    }

    @Test
    public void deleteCategoryCallsCategoryServiceGetCategory() {
        final int categoryId = 1;
        categoryController.deleteCategory(categoryId);
        verify(categoryService).deleteCategory(categoryId);
    }

}
