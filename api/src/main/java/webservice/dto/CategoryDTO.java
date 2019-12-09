package webservice.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class CategoryDTO {
    private Integer id;
    @NotEmpty(message = "Category name can not be empty")
    private String name;
    private Integer parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryDTO(){}

    public CategoryDTO(Integer id, String name, Integer parentId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
