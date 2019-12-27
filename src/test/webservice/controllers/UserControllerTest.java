package webservice.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webservice.dto.RegistrationDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.UserDTO;
import webservice.services.TransactionService;
import webservice.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private TransactionService transactionService;

    private ResponseEntity response;
    private List<TransactionDTO> transactions = new ArrayList<>();
    private List<UserDTO> users = new ArrayList<>();
    private UserDTO userDTO = new UserDTO();
    private final int categoryId = 1;
    private final int userId = 1;
    private final String occurrence = "monthly";
    private final String type = "income";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersReturnsStatus200() {
        ResponseEntity response = userController.getAllUsers();
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUsersCallsUserServiceGetAllUsers() {
        userController.getAllUsers();
        verify(userService).getAllUsers();
    }

    @Test
    public void getAllUsersReturnsListOfUsers() {
        when(userService.getAllUsers()).thenReturn(users);

        response = userController.getAllUsers();

        Assertions.assertEquals(users, response.getBody());
    }

    @Test
    public void getUserReturnsStatus200() {
        response = userController.getUser(userId);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getUserReturnsUserDTO() {
        when(userService.getUser(anyInt())).thenReturn(userDTO);

        response = userController.getUser(userId);

        Assertions.assertEquals(userDTO, response.getBody());
    }

    @Test
    public void getUserCallsUserServiceGetUser() {
        userController.getUser(userId);
        verify(userService).getUser(userId);
    }

    @Test
    public void deleteUserReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        response = userController.deleteUser(userId);

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void deleteUserReturnsBoolean() {
        when(userService.deleteUser(anyInt())).thenReturn(true);

        response = userController.deleteUser(userId);

        Assertions.assertEquals(true, response.getBody());
    }

    @Test
    public void deleteUserCallsUserServiceDeleteUser() {
        userController.deleteUser(userId);

        verify(userService).deleteUser(userId);
    }

    @Test
    public void updateUserReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        response = userController.updateUser(new UserDTO());

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void updateUserReturnsUserDTO() {
        when(userService.updateUser(any())).thenReturn(userDTO);

        response = userController.updateUser(userDTO);

        Assertions.assertEquals(userDTO, response.getBody());
    }

    @Test
    public void updateUserCallsUserServiceUpdateUser() {
        userController.updateUser(userDTO);

        verify(userService).updateUser(userDTO);
    }

    @Test
    public void addUserReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;
        response = userController.addUser(new RegistrationDTO());

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void addUserReturnsUserDTO() {
        when(userService.addUser(any())).thenReturn(userDTO);

        response = userController.addUser(new RegistrationDTO());

        Assertions.assertEquals(userDTO, response.getBody());
    }

    @Test
    public void addUserCallsUserServiceAddUser() {
        RegistrationDTO user = new RegistrationDTO();

        userController.addUser(user);

        verify(userService).addUser(user);
    }

    @Test
    public void getAllUserTransactionsReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        response = userController.getAllUserTransactions(userId);

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsReturnsListOfTransactions() {
        when(transactionService.getAllTransactionsByUserId(anyInt())).thenReturn(transactions);

        response = userController.getAllUserTransactions(1);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsCallsUserServiceGetAllUserTransactions() {
        userController.getAllUserTransactions(userId);

        verify(transactionService).getAllTransactionsByUserId(userId);
    }

    @Test
    public void getAllUserTransactionsByTypeReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        response = userController.getAllUserTransactionsByType(userId, type);

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByTypeReturnsListOfTransactions() {
        when(transactionService.getAllUserTransactionsByType(userId, type)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByType(userId, type);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByTypeCallsUserServiceGetAllUserTransactionsByType() {
        userController.getAllUserTransactionsByType(userId, type);

        verify(transactionService).getAllUserTransactionsByType(userId, type);
    }

    @Test
    public void getAllUserTransactionsByOccurrenceReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;
        response = userController.getAllUserTransactionsByOccurrence(userId, occurrence);

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByOccurrenceReturnsListOfTransactions() {
        when(transactionService.getAllUserTransactionsByOccurrence(userId, occurrence)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByOccurrence(userId, occurrence);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByOccurrenceCallsUserServiceGetAllUserTransactionsByOccurrence() {
        userController.getAllUserTransactionsByOccurrence(userId, type);

        verify(transactionService).getAllUserTransactionsByOccurrence(userId, type);
    }

    @Test
    public void getAllUserTransactionsByCategoryReturnsStatus200() {
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        response = userController.getAllUserTransactionsByCategory(userId, categoryId);

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByCategoryReturnsListOfTransactions() {
        when(transactionService.getAllUserTransactionsByCategory(userId, categoryId)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByCategory(userId, categoryId);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByCategoryCallsUserServiceGetAllUserTransactionsByCategory() {
        userController.getAllUserTransactionsByCategory(userId, categoryId);

        verify(transactionService).getAllUserTransactionsByCategory(userId, categoryId);
    }


}
