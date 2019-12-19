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

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }


    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping("{id}/children")
    public ResponseEntity<List<CategoryDTO>> getAllChildren(@PathVariable("id") int parentId) {
        return ResponseEntity.ok(categoryService.getChildren(parentId));
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
