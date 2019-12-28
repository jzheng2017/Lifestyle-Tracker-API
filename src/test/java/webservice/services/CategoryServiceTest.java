package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import webservice.dto.CategoryDTO;
import webservice.entities.Category;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.CategoryRepository;

import java.util.ArrayList;
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

    private final List<Category> list = new ArrayList<>();
    private final int parentId = 1;
    private final int categoryId = 1;
    private final String orderAsc = "asc";
    private final String orderDesc = "desc";
    private final String orderBy = "id";
    private final Sort.Direction sortDirectionAsc = Sort.Direction.ASC;
    private final Sort.Direction sortDirectionDesc = Sort.Direction.DESC;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllReturnsListOfCategories() {
        when(categoryRepository.findAll(Sort.by(sortDirectionAsc, orderBy))).thenReturn(list);

        Assertions.assertNotNull(categoryService.getAll(orderAsc, orderBy));
    }

    @Test
    public void getAllBySortDirectionCallsCategoryRepositoryFindAllFunctionWithCorrectSortDirection() {
        categoryService.getAll(orderAsc, orderBy);
        categoryService.getAll(orderDesc, orderBy);

        verify(categoryRepository).findAll(Sort.by(sortDirectionAsc, orderBy)); //categoryService.getAll(orderAsc, orderBy);
        verify(categoryRepository).findAll(Sort.by(sortDirectionDesc, orderBy)); //categoryService.getAll(orderDesc, orderBy);
    }

    @Test
    public void getAllMapsTheReturnedValues() {
        when(categoryRepository.findAll(Sort.by(sortDirectionAsc, orderBy))).thenReturn(list);
        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);

        list.add(mockedCategory); //add at least one category so it goes inside the map function

        categoryService.getAll(orderAsc, orderBy);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void getCategoryReturnsCategory() {
        final Optional<Category> category = Optional.of(mockedCategory);

        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);
        when(categoryRepository.findById(categoryId)).thenReturn(category);

        Assertions.assertEquals(mockedCategoryDTO, categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryMapsValue() {
        final Optional<Category> category = Optional.of(mockedCategory);

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        categoryService.getCategory(categoryId);

        verify(modelMapper).map(any(), any());
    }

    @Test
    public void getCategoryThrowsResourceNotFoundWhenNoCategoryReturned() {
        final Optional<Category> category = Optional.empty();

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryReturnsCorrectExceptionMessage() {
        final Optional<Category> category = Optional.empty();
        final String expectedMessage = "Category not found";
        ResourceNotFoundException exception;

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void getChildrenReturnsListOfCategories() {
        when(categoryRepository.findAllByParentId(parentId, Sort.by(sortDirectionAsc, orderBy))).thenReturn(list);

        Assertions.assertNotNull(categoryService.getChildren(parentId, orderAsc, orderBy));
    }

    @Test
    public void getChildrenBySortDirectionsCallsCategoryRepositoryFindAllByParentIdWithCorrectSortDirection() {
        categoryService.getChildren(parentId, orderAsc, orderBy);
        categoryService.getChildren(parentId, orderDesc, orderBy);

        verify(categoryRepository).findAllByParentId(parentId, Sort.by(sortDirectionAsc, orderBy)); //categoryService.getChildren(parentId, orderAsc, orderBy);
        verify(categoryRepository).findAllByParentId(parentId, Sort.by(sortDirectionDesc, orderBy)); //categoryService.getChildren(parentId, orderDesc, orderBy);
    }

    @Test
    public void getChildrenMapsTheReturnedValues() {
        when(categoryRepository.findAllByParentId(parentId, Sort.by(sortDirectionAsc, orderBy))).thenReturn(list);
        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);

        list.add(mockedCategory); //add at least one category so it goes inside the map function

        categoryService.getChildren(parentId, orderAsc, orderBy);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void createCategoryCallsCreateUpdateFunction(){
//        categoryService.createCategory(mockedCategoryDTO);
//        verify(categoryService).c
    }
}
