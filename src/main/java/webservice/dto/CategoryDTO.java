package webservice.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {
    private Integer id;
    @NotEmpty(message = "Category name can not be empty")
    private String name;
    private Integer parentId;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public CategoryDTO(int categoryId, String categoryName, Integer parentId) {
        this.id = categoryId;
        this.name = categoryName;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}