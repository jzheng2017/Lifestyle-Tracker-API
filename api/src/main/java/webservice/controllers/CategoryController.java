package webservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.CategoryDTO;
import webservice.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }


    @GetMapping("{id}")
    public ResponseEntity getCategory(@PathVariable("id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping("{id}/children")
    public ResponseEntity getAllChildren(@PathVariable("id") int parentId) {
        return ResponseEntity.ok(categoryService.getChildren(parentId));
    }

    @PostMapping("create")
    public ResponseEntity createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("update")
    public ResponseEntity updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }
}
