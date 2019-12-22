package webservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    /**
     * Get all categories
     *
     * @param order   order direction, ascending or descending
     * @param orderBy order by given field
     * @return a list of categories
     */
    public List<CategoryDTO> getAll(String order, String orderBy) {
        Sort.Direction sortDirection = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        return categoryRepository.findAll(Sort.by(sortDirection, orderBy)).stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get a specific category by id
     *
     * @param categoryId the id of a category
     * @return a specific category
     */
    public CategoryDTO getCategory(int categoryId) {
        return modelMapper.map(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found")), CategoryDTO.class);
    }

    /**
     * Get all children of a category
     *
     * @param parentId the id of a parent category
     * @param order    order direction, ascending or descending
     * @param orderBy  order by given field
     * @return a list of categories
     */
    public List<CategoryDTO> getChildren(int parentId, String order, String orderBy) {
        Sort.Direction sortDirection = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        return categoryRepository.findAllByParentId(parentId, Sort.by(sortDirection, orderBy)).stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());
    }

    /**
     * Add a category
     *
     * @param categoryDTO category object containing the category to be added
     * @return the added category
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return createUpdate(categoryDTO);
    }

    /**
     * Update a category
     *
     * @param categoryDTO category object containing the new values
     * @return the updated category
     */
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsById(categoryDTO.getId())) {
            return createUpdate(categoryDTO);
        } else {
            throw new ResourceNotFoundException("Category does not exist.");
        }
    }

    /**
     * Delete a category by id
     *
     * @param categoryId the id of a category
     * @return whether is has been deleted or not
     */
    public boolean deleteCategory(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        } else {
            throw new ResourceNotFoundException("Category does not exist");
        }
    }

    /**
     * Adds or update a category
     *
     * @param categoryDTO a category
     * @return the added or updated category
     */
    private CategoryDTO createUpdate(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }
}
