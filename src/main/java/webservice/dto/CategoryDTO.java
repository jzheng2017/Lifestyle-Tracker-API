package webservice.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {
    private Integer id;
    @NotEmpty(message = "Category name can not be empty")
    private String name;
    private CategoryDTO parent;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name, CategoryDTO parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public CategoryDTO(int categoryId, String categoryName, CategoryDTO parent) {
        this.id = categoryId;
        this.name = categoryName;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getParent() {
        return parent;
    }

    public void setParent(CategoryDTO parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
