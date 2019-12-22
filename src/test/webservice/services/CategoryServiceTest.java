package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    private final String order = "asc";
    private final String orderBy = "id";
    private final Sort.Direction expectedSortDirection = Sort.Direction.ASC;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllReturnsListOfCategories() {
        when(categoryRepository.findAll(Sort.by(expectedSortDirection, orderBy))).thenReturn(list);

        Assertions.assertNotNull(categoryService.getAll(order, orderBy));
    }

    @Test
    public void getAllCallsCategoryRepositoryFindAllFunction() {
        categoryService.getAll(order, orderBy);

        verify(categoryRepository).findAll(Sort.by(expectedSortDirection, orderBy));
    }

    @Test
    public void getAllMapsTheReturnedValues() {
        when(categoryRepository.findAll(Sort.by(expectedSortDirection, orderBy))).thenReturn(list);
        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);

        list.add(mockedCategory); //add at least one category so it goes inside the map function

        categoryService.getAll(order, orderBy);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }

    @Test
    public void getCategoryReturnsCategory() {
        final int categoryId = 1;
        final Optional<Category> category = Optional.of(mockedCategory);

        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);
        when(categoryRepository.findById(categoryId)).thenReturn(category);

        Assertions.assertEquals(mockedCategoryDTO, categoryService.getCategory(categoryId));
    }

    @Test
    public void getCategoryMapsValue() {
        final int categoryId = 1;
        final Optional<Category> category = Optional.of(mockedCategory);

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        categoryService.getCategory(categoryId);

        verify(modelMapper).map(any(), any());
    }

    @Test
    public void getCategoryThrowsResourceNotFoundWhenNoCategoryReturned() {
        final int categoryId = 1;
        final Optional<Category> category = Optional.empty();

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(categoryId));
    }

    @Test
    public void getChildrenReturnsListOfCategories() {
        when(categoryRepository.findAllByParentId(parentId, Sort.by(expectedSortDirection, orderBy))).thenReturn(list);

        Assertions.assertNotNull(categoryService.getChildren(parentId, order, orderBy));
    }

    @Test
    public void getChildrenMapsTheReturnedValues() {
        when(categoryRepository.findAllByParentId(parentId, Sort.by(expectedSortDirection, orderBy))).thenReturn(list);
        when(modelMapper.map(any(), any())).thenReturn(mockedCategoryDTO);

        list.add(mockedCategory); //add at least one category so it goes inside the map function

        categoryService.getChildren(parentId, order, orderBy);

        verify(modelMapper, atLeastOnce()).map(any(), any());
        verify(modelMapper, atMostOnce()).map(any(), any()); //list (in this test) contains only 1 category so it should be called at least 1 time and at most 1 time
    }
}
