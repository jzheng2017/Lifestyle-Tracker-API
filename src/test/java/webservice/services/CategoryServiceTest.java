package webservice.services;

import com.querydsl.core.types.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.entities.QCategory;
import webservice.exceptions.BadParameterException;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.CategoryRepository;
import webservice.util.mappers.CategoryMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    @InjectMocks
    @Spy
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private CategoryDTO mockedCategoryDTO;
    @Mock
    private Category mockedCategory;
    @Mock
    private Predicate predicate;
    @Mock
    private Pageable pageable;
    @Mock
    private Page<Category> pageList;

    private List<Category> list;

    private Optional<Category> category;

    private Optional<Category> emptyCategory;


    private final int parentId = 1;
    private final int categoryId = 1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        category = Optional.of(mockedCategory);
        emptyCategory = Optional.empty();
        list = new ArrayList<>(Arrays.asList(mockedCategory));
        when(categoryMapper.mapToCategoryDTO(mockedCategory)).thenReturn(mockedCategoryDTO);
        when(categoryMapper.mapToCategoryEntity(mockedCategoryDTO)).thenReturn(mockedCategory);
        when(categoryRepository.save(mockedCategory)).thenReturn(mockedCategory);
        when(categoryRepository.findById(categoryId)).thenReturn(category);
        when(mockedCategoryDTO.getId()).thenReturn(categoryId);
        when(pageList.getContent()).thenReturn(list);
    }

    @Test
    public void getAllReturnsListOfCategories() {
        when(categoryRepository.findAll(predicate, pageable)).thenReturn(pageList);

        Assertions.assertNotNull(categoryService.getAll(predicate, pageable));
    }

    @Test
    public void getAllByPredicateAndPageablePassesCorrectArgumentToRepositoryFunction() {
        when(categoryRepository.findAll(predicate, pageable)).thenReturn(pageList);

        categoryService.getAll(predicate, pageable);

        verify(categoryRepository).findAll(predicate, pageable);
    }

    @Test
    public void getAllPassesPredicateWithContentToRepositoryWhenPredicateIsInitiallyNull() {
        final Predicate expectedPredicate = QCategory.category.id.ne(-1);

        when(categoryRepository.findAll(expectedPredicate, pageable)).thenReturn(pageList);

        categoryService.getAll(null, pageable);

        verify(categoryRepository).findAll(expectedPredicate, pageable);
    }

    @Test
    public void getAllMapsTheReturnedValues() {
        when(categoryRepository.findAll(predicate, pageable)).thenReturn(pageList);

        categoryService.getAll(predicate, pageable);

        verify(categoryMapper, atLeastOnce()).mapToCategoryDTOList(any());
        verify(categoryMapper, atMostOnce()).mapToCategoryDTOList(any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void getCategoryReturnsCategory() {
        Assertions.assertEquals(mockedCategoryDTO, categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryMapsValue() {
        categoryService.getCategory(categoryId);

        verify(categoryMapper).mapToCategoryDTO(any());
    }

    @Test
    public void getCategoryThrowsResourceNotFoundWhenNoCategoryReturned() {
        when(categoryRepository.findById(categoryId)).thenReturn(emptyCategory);

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryReturnsCorrectExceptionMessage() {
        final String expectedMessage = "Category not found";
        ResourceNotFoundException thrownException;

        when(categoryRepository.findById(categoryId)).thenReturn(emptyCategory);

        thrownException = assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));

        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }

    @Test
    public void getChildrenReturnsListOfCategories() {
        final Predicate expectedPredicate = QCategory.category.parent.id.eq(parentId).and(predicate);

        when(categoryRepository.findAll(expectedPredicate, pageable)).thenReturn(pageList);

        Assertions.assertNotNull(categoryService.getChildren(parentId, predicate, pageable));
    }

    @Test
    public void getChildrenByPredicateAndPageablePassesCorrectArgumentToRepositoryFunction() {
        final Predicate expectedPredicate = QCategory.category.parent.id.eq(parentId).and(predicate);

        when(categoryRepository.findAll(expectedPredicate, pageable)).thenReturn(pageList);

        categoryService.getChildren(parentId, null, pageable);

        verify(categoryRepository).findAll(expectedPredicate, pageable);
    }

    @Test
    public void getChildrenMapsTheReturnedValues() {
        final Predicate expectedPredicate = QCategory.category.parent.id.eq(parentId).and(predicate);

        when(categoryRepository.findAll(expectedPredicate, pageable)).thenReturn(pageList);

        categoryService.getChildren(parentId, predicate, pageable);

        verify(categoryMapper, atLeastOnce()).mapToCategoryDTOList(any());
        verify(categoryMapper, atMostOnce()).mapToCategoryDTOList(any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void createCategoryReturnsCategoryDTO() {
        Assert.assertNotNull(categoryService.createCategory(mockedCategoryDTO));
    }

    @Test
    public void createCategoryThrowsBadParameterExceptionIfCategoryDTOIsNull() {
        assertThrows(BadParameterException.class, () -> categoryService.createCategory(null));
    }

    @Test
    public void createCategoryCallsRepositorySaveFunction() {
        categoryService.createCategory(mockedCategoryDTO);

        verify(categoryRepository).save(mockedCategory);
    }

    @Test
    public void createCategoryMapsCategoryDTOToEntity() {
        categoryService.createCategory(mockedCategoryDTO);

        verify(categoryMapper).mapToCategoryEntity(mockedCategoryDTO);
    }

    @Test
    public void createCategoryMapsSavedEntityBackToDTO() {
        categoryService.createCategory(mockedCategoryDTO);

        verify(categoryMapper).mapToCategoryDTO(mockedCategory);
    }

    @Test
    public void updateCategoryReturnsCategoryDTO() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        assertNotNull(categoryService.updateCategory(mockedCategoryDTO));
    }

    @Test(expected = BadParameterException.class)
    public void updateCategoryThrowsBadParameterExceptionWhenCategoryDTOIsNull() {
        categoryService.updateCategory(null);
    }

    @Test
    public void updateCategoryBadParameterExceptionsThrowsCorrectMessage() {
        final String expectedMessage = "Category can not be null";
        BadParameterException thrownException;

        thrownException = assertThrows(BadParameterException.class, () -> categoryService.updateCategory(null));

        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateCategoryThrowsResourceNotFoundExceptionWhenCategoryRepositoryExistByIdReturnsFalse() {
        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        categoryService.updateCategory(mockedCategoryDTO);
    }

    @Test
    public void updateCategoryResourceNotFoundExceptionsThrowsCorrectMessage() {
        final String expectedMessage = "Category does not exist";
        ResourceNotFoundException thrownException;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        thrownException = assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(mockedCategoryDTO));

        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }
    @Test
    public void updateCategoryCallsCategoryRepositorySaveFunctionIfCategoryExists() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.updateCategory(mockedCategoryDTO);

        verify(categoryRepository).save(mockedCategory);
    }


    @Test
    public void updateCategoryMapsCategoryDTOToEntity() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.updateCategory(mockedCategoryDTO);

        verify(categoryMapper).mapToCategoryEntity(mockedCategoryDTO);
    }

    @Test
    public void updateCategoryMapsSavedEntityBackToDTO() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.updateCategory(mockedCategoryDTO);

        verify(categoryMapper).mapToCategoryDTO(mockedCategory);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteCategoryThrowsResourceNotFoundExceptionIfCategoryExistByIdReturnsFalse() {
        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        categoryService.deleteCategory(categoryId);
    }

    @Test
    public void deleteCategoryCallsCategoryRepositoryDeleteWhenCategoryExistsByIdReturnsReturn() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository).deleteById(categoryId);
    }

    @Test
    public void deleteCategoryReturnsTrueWhenCategoryGetsDeleted() {
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        Assertions.assertTrue(categoryService.deleteCategory(categoryId));
    }

    @Test
    public void deleteCategoryResourceNotFoundExceptionsThrowsCorrectMessage() {
        final String expectedMessage = "Category does not exist";
        ResourceNotFoundException thrownException;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        thrownException = assertThrows(ResourceNotFoundException.class, () -> categoryService.deleteCategory(categoryId));

        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }

}
