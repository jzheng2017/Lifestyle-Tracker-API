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
        ResponseEntity response;
        List<UserDTO> users = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(users);

        response = userController.getAllUsers();

        Assertions.assertEquals(users, response.getBody());
    }

    @Test
    public void getUserReturnsStatus200() {
        final int userId = 1;
        ResponseEntity response = userController.getUser(userId);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getUserReturnsUserDTO() {
        ResponseEntity response;
        UserDTO userDTO = new UserDTO();
        final int userId = 1;

        when(userService.getUser(anyInt())).thenReturn(userDTO);

        response = userController.getUser(userId);

        Assertions.assertEquals(userDTO, response.getBody());
    }

    @Test
    public void getUserCallsUserServiceGetUser() {
        final int userId = 1;
        userController.getUser(userId);
        verify(userService).getUser(userId);
    }

    @Test
    public void deleteUserReturnsStatus200() {
        final int userId = 1;
        ResponseEntity response = userController.deleteUser(userId);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void deleteUserReturnsBoolean() {
        ResponseEntity response;
        final int userId = 1;

        when(userService.deleteUser(anyInt())).thenReturn(true);

        response = userController.deleteUser(userId);

        Assertions.assertEquals(true, response.getBody());
    }

    @Test
    public void deleteUserCallsUserServiceDeleteUser() {
        final int userId = 1;

        userController.deleteUser(userId);

        verify(userService).deleteUser(userId);
    }

    @Test
    public void updateUserReturnsStatus200() {
        ResponseEntity response = userController.updateUser(new UserDTO());
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void updateUserReturnsUserDTO() {
        ResponseEntity response;
        UserDTO userDTO = new UserDTO();

        when(userService.updateUser(any())).thenReturn(userDTO);

        response = userController.updateUser(userDTO);

        Assertions.assertEquals(userDTO, response.getBody());
    }

    @Test
    public void updateUserCallsUserServiceUpdateUser() {
        UserDTO user = new UserDTO();

        userController.updateUser(user);

        verify(userService).updateUser(user);
    }

    @Test
    public void addUserReturnsStatus200() {
        ResponseEntity response = userController.addUser(new RegistrationDTO());
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void addUserReturnsUserDTO() {
        ResponseEntity response;
        UserDTO userDTO = new UserDTO();

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
        final int userId = 1;
        ResponseEntity response = userController.getAllUserTransactions(userId);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsReturnsListOfTransactions() {
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();

        when(transactionService.getAllTransactionsByUserId(anyInt())).thenReturn(transactions);

        response = userController.getAllUserTransactions(1);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsCallsUserServiceGetAllUserTransactions() {
        final int userId = 1;

        userController.getAllUserTransactions(userId);

        verify(transactionService).getAllTransactionsByUserId(userId);
    }

    @Test
    public void getAllUserTransactionsByTypeReturnsStatus200() {
        final String type = "income";
        final int userId = 1;
        ResponseEntity response = userController.getAllUserTransactionsByType(userId, type);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByTypeReturnsListOfTransactions() {
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();
        final String type = "income";
        final int userId = 1;

        when(transactionService.getAllUserTransactionsByType(userId, type)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByType(userId, type);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByTypeCallsUserServiceGetAllUserTransactionsByType() {
        final String type = "income";
        final int userId = 1;

        userController.getAllUserTransactionsByType(userId, type);
        verify(transactionService).getAllUserTransactionsByType(userId, type);
    }

    @Test
    public void getAllUserTransactionsByOccurrenceReturnsStatus200() {
        final String occurrence = "monthly";
        final int userId = 1;
        ResponseEntity response = userController.getAllUserTransactionsByOccurrence(userId, occurrence);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByOccurrenceReturnsListOfTransactions() {
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();
        final String occurrence = "monthly";
        final int userId = 1;

        when(transactionService.getAllUserTransactionsByOccurence(userId, occurrence)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByOccurrence(userId, occurrence);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByOccurrenceCallsUserServiceGetAllUserTransactionsByOccurrence() {
        final String type = "income";
        final int userId = 1;

        userController.getAllUserTransactionsByOccurrence(userId, type);
        verify(transactionService).getAllUserTransactionsByOccurence(userId, type);
    }

    @Test
    public void getAllUserTransactionsByCategoryReturnsStatus200() {
        final int categoryId = 1;
        final int userId = 1;
        ResponseEntity response = userController.getAllUserTransactionsByCategory(userId, categoryId);
        final HttpStatus expectedHttpCode = HttpStatus.OK;

        Assertions.assertEquals(expectedHttpCode, response.getStatusCode());
    }

    @Test
    public void getAllUserTransactionsByCategoryReturnsListOfTransactions() {
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();
        final int categoryId = 1;
        final int userId = 1;

        when(transactionService.getAllUserTransactionsByCategory(userId, categoryId)).thenReturn(transactions);

        response = userController.getAllUserTransactionsByCategory(userId, categoryId);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllUserTransactionsByCategoryCallsUserServiceGetAllUserTransactionsByCategory() {
        final int categoryId = 1;
        final int userId = 1;

        userController.getAllUserTransactionsByCategory(userId, categoryId);
        verify(transactionService).getAllUserTransactionsByCategory(userId, categoryId);
    }


}
