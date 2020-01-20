package webservice.util.mappers;

import org.springframework.stereotype.Service;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMapper extends ClassMapper {

    public List<CategoryDTO> mapToCategoryDTOList(List<Category> categories) {
        return categories
                .stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO mapToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category mapToCategoryEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
