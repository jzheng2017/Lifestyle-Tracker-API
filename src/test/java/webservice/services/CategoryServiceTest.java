package webservice.services;

import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.entities.QCategory;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    @InjectMocks
    @Spy
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;
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
        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);
        when(categoryRepository.findById(categoryId)).thenReturn(category);
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
    public void getAllMapsTheReturnedValues() {
        when(categoryRepository.findAll(predicate, pageable)).thenReturn(pageList);

        categoryService.getAll(predicate, pageable);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void getCategoryReturnsCategory() {
        Assertions.assertEquals(mockedCategoryDTO, categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryMapsValue() {
        categoryService.getCategory(categoryId);

        verify(modelMapper).map(any(), any());
    }

    @Test
    public void getCategoryThrowsResourceNotFoundWhenNoCategoryReturned() {
        when(categoryRepository.findById(categoryId)).thenReturn(emptyCategory);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryReturnsCorrectExceptionMessage() {
        final String expectedMessage = "Category not found";
        ResourceNotFoundException exception;

        when(categoryRepository.findById(categoryId)).thenReturn(emptyCategory);

        exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));

        Assertions.assertEquals(expectedMessage, exception.getMessage());
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

        categoryService.getChildren(parentId, predicate, pageable);

        verify(categoryRepository).findAll(expectedPredicate, pageable);
    }

    @Test
    public void getChildrenMapsTheReturnedValues() {
        final Predicate expectedPredicate = QCategory.category.parent.id.eq(parentId).and(predicate);

        when(categoryRepository.findAll(expectedPredicate, pageable)).thenReturn(pageList);

        categoryService.getChildren(parentId, predicate, pageable);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void createCategoryCallsCreateUpdateFunction() {
//        categoryService.createCategory(mockedCategoryDTO);
//        verify(categoryService).c
    }
}
