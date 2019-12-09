package webservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CategoryDTO> getAll() {
        return ((List<Category>) categoryRepository.findAll()).stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());
    }

    public CategoryDTO getCategory(int categoryId) {
        return modelMapper.map(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found")), CategoryDTO.class);
    }

    public List<CategoryDTO> getChildren(int parentId) {
        return categoryRepository.findAllByParentId(parentId).stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return createUpdate(categoryDTO);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsById(categoryDTO.getId())) {
            return createUpdate(categoryDTO);
        } else {
            throw new ResourceNotFoundException("Category does not exist.");
        }
    }

    private CategoryDTO createUpdate(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }
}
