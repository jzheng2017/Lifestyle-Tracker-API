package webservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.CategoryDTO;
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
     * @param order   the order in which the list should be ordered (ex. ascending)
     * @param orderBy the field that should be ordered on
     * @return ResponseEntity object containing a list of categories
     */
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@RequestParam(value = "order", defaultValue = "asc") String order, @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok(categoryService.getAll(order, orderBy));
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
     * @param order    the order in which the list should be ordered (ex. ascending)
     * @param orderBy  the field that should be ordered on
     * @return ResponseEntity object containing a list of categories
     */
    @GetMapping("{id}/children")
    public ResponseEntity<List<CategoryDTO>> getAllChildren(@PathVariable("id") int parentId,
                                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                                            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok(categoryService.getChildren(parentId, order, orderBy));
    }

    /**
     * Create a category
     *
     * @param categoryDTO the category request object
     * @return ResponseEntity object containing the added category
     */
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    /**
     * Update a category
     *
     * @param categoryDTO the category containing the new values
     * @return ResponseEntity object containing the updated category
     */
    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    /**
     * Delete a category
     *
     * @param categoryId the id of a category
     * @return ResponseEntity object containing a boolean value whether the category has been successfully deleted
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
