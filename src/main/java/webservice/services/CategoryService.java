package webservice.services;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.entities.QCategory;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.CategoryRepository;
import webservice.util.mappers.CategoryMapper;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    /**
     * Get all categories
     *
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return a list of categories
     */
    public List<CategoryDTO> getAll(Predicate predicate, Pageable pageable) {
        predicate = returnPredicateWhenNull(predicate);
        List<Category> categories = categoryRepository.findAll(predicate, pageable).getContent();

        return categoryMapper.mapToCategoryDTOList(categories);
    }

    /**
     * Get a specific category by id
     *
     * @param categoryId the id of a category
     * @return a specific category
     */
    public CategoryDTO getCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return categoryMapper.mapToCategoryDTO(category);
    }

    /**
     * Get all children of a category
     *
     * @param parentId  the id of a parent category
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return a list of categories
     */
    public List<CategoryDTO> getChildren(int parentId, Predicate predicate, Pageable pageable) {
        predicate = QCategory.category.parent.id.eq(parentId).and(predicate);
        List<Category> categories = categoryRepository.findAll(predicate, pageable).getContent();

        return categoryMapper.mapToCategoryDTOList(categories);
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
        Category categoryEntity = categoryMapper.mapToCategoryEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(categoryEntity);

        return categoryMapper.mapToCategoryDTO(savedCategory);
    }

    private Predicate returnPredicateWhenNull(Predicate predicate) {
        if (predicate == null) {
            return QCategory.category.id.ne(-1); // bug workaround of QueryDSL Web Support, it returns null when no matching criteria is passed in
        }
        return predicate;
    }
}
