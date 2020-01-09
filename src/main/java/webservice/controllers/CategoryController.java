package webservice.controllers;


import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.services.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all categories
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return ResponseEntity object containing a list of categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@QuerydslPredicate(root = Category.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAll(predicate, pageable));
    }

    /**
     * Get a single category
     *
     * @param categoryId the id of a category
     * @return ResponseEntity object containing a category object
     */
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    /**
     * Get all children of a category
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param parentId the id of a parent category
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return ResponseEntity object containing a list of categories
     */
    @GetMapping("{id}/children")
    public ResponseEntity<List<CategoryDTO>> getAllChildren(@PathVariable("id") int parentId, @QuerydslPredicate(root = Category.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(categoryService.getChildren(parentId, predicate, pageable));
    }

    /**
     * Create a category
     *
     * @param categoryDTO the category request object
     * @return ResponseEntity object containing the added category
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    /**
     * Update a category
     *
     * @param categoryDTO the category containing the new values
     * @return ResponseEntity object containing the updated category
     */
    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    /**
     * Delete a category
     *
     * @param categoryId the id of a category
     * @return ResponseEntity object containing a boolean value whether the category has been successfully deleted
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
