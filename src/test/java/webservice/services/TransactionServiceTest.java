package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.entities.Transaction;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.TransactionOccurrenceTypeRepository;
import webservice.repositories.TransactionRepository;
import webservice.util.mappers.TransactionMapper;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository mockedTransactionRepository;
    @Mock
    private TransactionOccurrenceTypeRepository mockedTransactionOccurrenceTypeRepository;
    @Mock
    private TransactionMapper mockedTransactionMapper;
    @Mock
    private Transaction mockedTransactionEntity;
    @Mock
    private Transaction mockedSavedTransactionEntity;
    @Mock
    private TransactionDTO mockedTransactionDTO;
    @Mock
    private TransactionRequestDTO mockedTransactionRequestDTO;

    private Optional<Transaction> optionalTransaction;

    private final int transactionId = 1;
    private final int fakeTransactionId = -1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        optionalTransaction = Optional.of(mockedTransactionEntity);
        when(mockedTransactionMapper.mapToTransactionDTO(mockedTransactionEntity)).thenReturn(mockedTransactionDTO);
        when(mockedTransactionRepository.findById(transactionId)).thenReturn(optionalTransaction);
        when(mockedTransactionMapper.mapToTransactionEntity(mockedTransactionRequestDTO)).thenReturn(mockedTransactionEntity);
        when(mockedTransactionRepository.save(mockedTransactionEntity)).thenReturn(mockedSavedTransactionEntity);
    }

    @Test
    public void getTransactionReturnsTransactionDTO() {
        Assertions.assertNotNull(transactionService.getTransaction(transactionId));
    }

    @Test
    public void getTransactionCallsTransactionRepositoryFindById() {
        transactionService.getTransaction(transactionId);

        verify(mockedTransactionRepository).findById(transactionId);
    }

    @Test
    public void getTransactionCallsTransactionMapperMapTransactionToDTO() {
        transactionService.getTransaction(transactionId);

        verify(mockedTransactionMapper).mapToTransactionDTO(mockedTransactionEntity);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getTransactionThrowsResourceNotFoundExceptionIfNoTransactionReturns() {
        transactionService.getTransaction(fakeTransactionId);
    }

    @Test
    public void getTransactionThrowsCorrectExceptionMessage() {
        final String expectedMessage = "Transaction not found";

        final String actualMessage = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getTransaction(fakeTransactionId)).getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void insertTransactionCallsTransactionMapperMapToTransactionEntity() {
        transactionService.insertTransaction(mockedTransactionRequestDTO);

        verify(mockedTransactionMapper).mapToTransactionEntity(mockedTransactionRequestDTO);
    }

    @Test
    public void insertTransactionCallsTransactionRepositorySave() {
        transactionService.insertTransaction(mockedTransactionRequestDTO);

        verify(mockedTransactionRepository).save(mockedTransactionEntity);
    }

    @Test
    public void insertTransactionCallsTransactionMapperMapToTransactionDTO() {
        transactionService.insertTransaction(mockedTransactionRequestDTO);

        verify(mockedTransactionMapper).mapToTransactionDTO(mockedSavedTransactionEntity);
    }
}
