package webservice.services;

import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webservice.dto.RegistrationDTO;
import webservice.dto.UserDTO;
import webservice.entities.QUser;
import webservice.entities.User;
import webservice.exceptions.BadParameterException;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.UserRepository;
import webservice.services.interfaces.HashService;
import webservice.util.mappers.UserMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserMapper mockedUserMapper;
    @Mock
    private UserRepository mockedUserRepository;
    @Mock
    private HashService mockedHashService;
    @Mock
    private Predicate mockedPredicate;
    @Mock
    private Pageable mockedPageable;
    @Mock
    private Page<User> userPage;
    @Mock
    private List<User> userList;
    @Mock
    private UserDTO mockedUserDTO;
    @Mock
    private RegistrationDTO mockedRegistrationDTO;
    @Mock
    private User mockedUser;
    @Mock
    private Optional<User> mockedOptionalUser;

    private final String username = "test";

    private final int userId = 1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mockedUserRepository.findAll(mockedPredicate, mockedPageable)).thenReturn(userPage);
        when(userPage.toList()).thenReturn(userList);
        when(mockedRegistrationDTO.getUsername()).thenReturn(username);
    }


    @Test
    public void getAllPassesPredicateWithContentToRepositoryWhenPredicateIsInitiallyNull() {
        final Predicate expectedPredicate = QUser.user.id.ne(-1);

        when(mockedUserRepository.findAll(expectedPredicate, mockedPageable)).thenReturn(userPage);

        userService.getAllUsers(null, mockedPageable);

        verify(mockedUserRepository).findAll(expectedPredicate, mockedPageable);
    }

    @Test
    public void getAllPassesCorrectPredicateToRepository() {
        userService.getAllUsers(mockedPredicate, mockedPageable);

        verify(mockedUserRepository).findAll(mockedPredicate, mockedPageable);
    }

    @Test
    public void getAllMapsReturnedUserEntitiesToDTOs() {
        userService.getAllUsers(mockedPredicate, mockedPageable);

        verify(mockedUserMapper).mapToUserDTOList(userList);
    }

    @Test
    public void getAllConvertsResultToList() {
        userService.getAllUsers(mockedPredicate, mockedPageable);

        verify(userPage).toList();
    }

    @Test
    public void getAllReturnsListOfUsers() {
        Assertions.assertNotNull(userService.getAllUsers(mockedPredicate, mockedPageable));
    }

    @Test
    public void getUserReturnsUser() {
        when(mockedUserRepository.findById(userId)).thenReturn(mockedOptionalUser);
        when(mockedUserMapper.mapToUserDTO(mockedUser)).thenReturn(mockedUserDTO);

        Assertions.assertNotNull(userService.getUser(userId));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getUserThrowsResourceNotFoundExceptionWhenFindByIdReturnsFalse() {
        when(mockedUserRepository.findById(userId)).thenReturn(Optional.empty());

        userService.getUser(userId);
    }

    @Test
    public void getUserThrowsCorrectExceptionMessage() {
        final String expectedMessage = "No user found";

        when(mockedUserRepository.findById(userId)).thenReturn(Optional.empty());

        final String actualMessage = Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.getUser(userId)).getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getUserCallsRepositoryFindById() {
        when(mockedUserRepository.findById(userId)).thenReturn(mockedOptionalUser);

        userService.getUser(userId);

        verify(mockedUserRepository).findById(userId);
    }

    @Test
    public void deleteUserCallsRepositoryExistsById() {
        when(mockedUserRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(mockedUserRepository).existsById(userId);
    }

    @Test
    public void deleteUserCallsRepositoryDeleteByIdWhenExistByIdReturnsTrue() {
        when(mockedUserRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(mockedUserRepository).deleteById(userId);
    }

    @Test
    public void deleteUserReturnsTrueWhenUserGetsDeleted() {
        when(mockedUserRepository.existsById(userId)).thenReturn(true);

        assertTrue(userService.deleteUser(userId));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteUserThrowsResourceNotFoundExceptionWhenRepositoryDeleteByIdReturnsFalse() {
        when(mockedUserRepository.existsById(userId)).thenReturn(false);

        userService.deleteUser(userId);
    }

    @Test
    public void deleteUserThrowsCorrectExceptionMessage() {
        final String expectedMessage = "User can not be deleted. The given user does not exist.";

        when(mockedUserRepository.existsById(userId)).thenReturn(false);

        final String actualMessage = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId)).getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test(expected = BadParameterException.class)
    public void addUserThrowsBadParameterExceptionWhenRegistrationDtoIsNull() {
        userService.addUser(null);
    }

    @Test
    public void addUserBadParameterExceptionThrowsCorrectMessage() {
        final String expectedMessage = "The registration is null";

        final String actualMessage = assertThrows(BadParameterException.class, () -> userService.addUser(null)).getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addUserRepositoryFindByUsernameThrowsDuplicateEntryExceptionWhenUsernameIsAlreadyPresent() {
        when(mockedUserRepository.findByUsername(username)).thenReturn(mockedOptionalUser);
        when(mockedOptionalUser.isPresent()).thenReturn(true);
    }


}
