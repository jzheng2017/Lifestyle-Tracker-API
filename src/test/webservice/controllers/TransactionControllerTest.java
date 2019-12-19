package webservice.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.services.TransactionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {
    @InjectMocks
    private TransactionController transactionController;
    @Mock
    private TransactionService transactionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTransactionsReturnsStatus200() {
        ResponseEntity response = transactionController.getAllTransactions();
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getAllTransactionsReturnsListOfTransactions() {
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        response = transactionController.getAllTransactions();

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllTransactionsCallsTransactionServiceGetAllTransactions() {
        transactionController.getAllTransactions();
        verify(transactionService).getAllTransactions();
    }

    @Test
    public void getAllTransactionsByTypeReturnsStatus200() {
        final String type = "income";
        ResponseEntity response = transactionController.getAllTransactionsByType(type);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getAllTransactionsByTypeReturnsListOfTransactions() {
        final String type = "income";
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();

        when(transactionService.getAllTransactionsByType(type)).thenReturn(transactions);

        response = transactionController.getAllTransactionsByType(type);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllTransactionsByTypeCallsTransactionServiceGetAllTransactionsByType() {
        final String type = "income";
        transactionController.getAllTransactionsByType(type);
        verify(transactionService).getAllTransactionsByType(type);
    }

    @Test
    public void getAllTransactionsByOccurrenceReturnsStatus200() {
        final String occurrence = "monthly";
        ResponseEntity response = transactionController.getAllTransactionsByOccurrence(occurrence);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getAllTransactionsByOccurrenceReturnsListOfTransactions() {
        final String occurrence = "monthly";
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();

        when(transactionService.getAllTransactionsByOccurrence(occurrence)).thenReturn(transactions);

        response = transactionController.getAllTransactionsByOccurrence(occurrence);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllTransactionsByOccurrenceCallsTransactionServiceGetAllTransactionsByOccurrence() {
        final String occurrence = "occurrence";
        transactionController.getAllTransactionsByOccurrence(occurrence);
        verify(transactionService).getAllTransactionsByOccurrence(occurrence);
    }

    @Test
    public void getAllTransactionsByCategoryReturnsStatus200() {
        final String category = "monthly";
        ResponseEntity response = transactionController.getAllTransactionsByOccurrence(category);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getAllTransactionsByCategoryReturnsListOfTransactions() {
        final int category = 1;
        ResponseEntity response;
        List<TransactionDTO> transactions = new ArrayList<>();

        when(transactionService.getAllTransactionsByCategory(category)).thenReturn(transactions);

        response = transactionController.getAllTransactionsByCategory(category);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllTransactionsByCategoryCallsTransactionServiceGetAllTransactionsByCategory() {
        final int category = 1;

        transactionController.getAllTransactionsByCategory(category);

        verify(transactionService).getAllTransactionsByCategory(category);
    }

    @Test
    public void getTransactionReturnsStatus200() {
        final int transactionId = 1;
        ResponseEntity response = transactionController.getTransaction(transactionId);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getTransactionReturnsTransactionDTO() {
        final int transactionId = 1;
        ResponseEntity response;
        TransactionDTO transaction = new TransactionDTO();

        when(transactionService.getTransaction(transactionId)).thenReturn(transaction);

        response = transactionController.getTransaction(transactionId);

        Assertions.assertEquals(transaction, response.getBody());
    }

    @Test
    public void getTransactionCallsTransactionServiceGetTransaction() {
        final int transactionId = 1;

        transactionController.getTransaction(transactionId);

        verify(transactionService).getTransaction(transactionId);
    }

    @Test
    public void addTransactionReturnsStatus200() {
        TransactionDTO transaction = new TransactionDTO();
        ResponseEntity response = transactionController.addTransaction(transaction);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void addTransactionReturnsTransactionDTO() {
        ResponseEntity response;
        TransactionDTO transaction = new TransactionDTO();

        when(transactionService.insertTransaction(transaction)).thenReturn(transaction);

        response = transactionController.addTransaction(transaction);

        Assertions.assertEquals(transaction, response.getBody());
    }

    @Test
    public void addTransactionCallsTransactionServiceInsertTransaction() {
        TransactionDTO transaction = new TransactionDTO();

        transactionController.addTransaction(transaction);

        verify(transactionService).insertTransaction(transaction);
    }

    @Test
    public void updateTransactionReturnsStatus200() {
        final TransactionDTO transaction = new TransactionDTO();
        ResponseEntity response = transactionController.updateTransaction(transaction);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void updateTransactionReturnsTransactionDTO() {

        ResponseEntity response;
        TransactionDTO transaction = new TransactionDTO();

        when(transactionService.updateTransaction(transaction)).thenReturn(transaction);

        response = transactionController.updateTransaction(transaction);

        Assertions.assertEquals(transaction, response.getBody());
    }

    @Test
    public void updateTransactionCallsTransactionServiceUpdateTransaction() {
        TransactionDTO transaction = new TransactionDTO();

        transactionController.updateTransaction(transaction);

        verify(transactionService).updateTransaction(transaction);
    }

    @Test
    public void deleteTransactionReturnsStatus200() {
        final int transactionId = 1;
        ResponseEntity response = transactionController.deleteTransaction(transactionId);
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void deleteTransactionReturnsBoolean() {
        ResponseEntity response;
        final int transactionId = 1;

        when(transactionService.deleteTransaction(transactionId)).thenReturn(true);

        response = transactionController.deleteTransaction(transactionId);

        Assertions.assertEquals(true, response.getBody());
    }

    @Test
    public void deleteTransactionCallsTransactionServiceGetTransaction() {
        final int transactionId = 1;

        transactionController.deleteTransaction(transactionId);

        verify(transactionService).deleteTransaction(transactionId);
    }


    @Test
    public void getAllTransactionTypesReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final HttpStatus actualStatusCode = transactionController.getAllTransactionTypes().getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void getAllTransactionTypesReturnsListOfTransactionTypes() {
        final List<TransactionTypeDTO> list = new ArrayList<>();
        ResponseEntity response;

        when(transactionService.getAllTransactionTypes()).thenReturn(list);

        response = transactionController.getAllTransactionTypes();

        Assertions.assertEquals(list, response.getBody());
    }

    @Test
    public void getAllTransactionTypesCallsTransactionServiceGetAllTransactionTypes() {
        transactionController.getAllTransactionTypes();
        verify(transactionService).getAllTransactionTypes();
    }

    @Test
    public void getAllTransactionOccurrenceTypesReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;
        final HttpStatus actualStatusCode = transactionController.getAllTransactionOccurrenceTypes().getStatusCode();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void getAllTransactionOccurrenceTypesReturnsListOfTransactionOccurrenceTypes() {
        final List<OccurrenceDTO> list = new ArrayList<>();
        ResponseEntity response;

        when(transactionService.getAllTransactionOccurrenceTypes()).thenReturn(list);

        response = transactionController.getAllTransactionOccurrenceTypes();

        Assertions.assertEquals(list, response.getBody());
    }

    @Test
    public void getAllTransactionOccurrenceTypesCallsTransactionServiceGetAllTransactionOccurrenceTypes() {
        transactionController.getAllTransactionOccurrenceTypes();
        verify(transactionService).getAllTransactionOccurrenceTypes();
    }


}
