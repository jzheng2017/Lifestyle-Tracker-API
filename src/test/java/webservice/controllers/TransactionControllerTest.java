package webservice.controllers;

import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
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
    @Mock
    private TransactionRequestDTO transactionRequestDTO;
    @Mock
    private TransactionDTO transactionDTO;
    @Mock
    private Predicate predicate;
    @Mock
    private Pageable pageable;

    private ResponseEntity response;
    private List<TransactionDTO> transactions = new ArrayList<>();
    private final String type = "income";
    private final int categoryId = 1;
    private final String order = "asc";
    private final String orderBy = "id";
    private final String category = "monthly";
    private final String occurrence = "monthly";
    private final int transactionId = 1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTransactionsReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        response = transactionController.getAllTransactions(predicate, pageable);

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getAllTransactionsReturnsListOfTransactions() {

        when(transactionService.getAllTransactions(predicate, pageable)).thenReturn(transactions);

        response = transactionController.getAllTransactions(predicate, pageable);

        Assertions.assertEquals(transactions, response.getBody());
    }

    @Test
    public void getAllTransactionsCallsTransactionServiceGetAllTransactions() {
        transactionController.getAllTransactions(predicate, pageable);

        verify(transactionService).getAllTransactions(predicate, pageable);
    }

    @Test
    public void getTransactionReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        response = transactionController.getTransaction(transactionId);

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void getTransactionReturnsTransactionDTO() {
        when(transactionService.getTransaction(transactionId)).thenReturn(transactionDTO);

        response = transactionController.getTransaction(transactionId);

        Assertions.assertEquals(transactionDTO, response.getBody());
    }

    @Test
    public void getTransactionCallsTransactionServiceGetTransaction() {
        transactionController.getTransaction(transactionId);

        verify(transactionService).getTransaction(transactionId);
    }

    @Test
    public void addTransactionReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        response = transactionController.addTransaction(transactionRequestDTO);

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void addTransactionReturnsTransactionDTO() {
        when(transactionService.insertTransaction(transactionRequestDTO)).thenReturn(transactionDTO);

        response = transactionController.addTransaction(transactionRequestDTO);

        Assertions.assertEquals(transactionDTO, response.getBody());
    }

    @Test
    public void addTransactionCallsTransactionServiceInsertTransaction() {
        transactionController.addTransaction(transactionRequestDTO);

        verify(transactionService).insertTransaction(transactionRequestDTO);
    }

    @Test
    public void updateTransactionReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        response = transactionController.updateTransaction(transactionRequestDTO);

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void updateTransactionReturnsTransactionDTO() {
        when(transactionService.updateTransaction(transactionRequestDTO)).thenReturn(transactionDTO);

        response = transactionController.updateTransaction(transactionRequestDTO);

        Assertions.assertEquals(transactionDTO, response.getBody());
    }

    @Test
    public void updateTransactionCallsTransactionServiceUpdateTransaction() {
        transactionController.updateTransaction(transactionRequestDTO);

        verify(transactionService).updateTransaction(transactionRequestDTO);
    }

    @Test
    public void deleteTransactionReturnsStatus200() {
        final HttpStatus expectedStatusCode = HttpStatus.OK;

        response = transactionController.deleteTransaction(transactionId);

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void deleteTransactionReturnsBoolean() {
        when(transactionService.deleteTransaction(transactionId)).thenReturn(true);

        response = transactionController.deleteTransaction(transactionId);

        Assertions.assertEquals(true, response.getBody());
    }

    @Test
    public void deleteTransactionCallsTransactionServiceGetTransaction() {
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
