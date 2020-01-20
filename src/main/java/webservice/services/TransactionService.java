package webservice.services;

import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.entities.QTransaction;
import webservice.entities.Transaction;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.TransactionOccurrenceTypeRepository;
import webservice.repositories.TransactionRepository;
import webservice.repositories.TransactionTypeRepository;
import webservice.util.mappers.TransactionMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionOccurrenceTypeRepository transactionOccurrenceTypeRepository;
    private TransactionMapper transactionMapper;

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setTransactionTypeRepository(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Autowired
    public void setTransactionOccurrenceTypeRepository(TransactionOccurrenceTypeRepository transactionOccurrenceTypeRepository) {
        this.transactionOccurrenceTypeRepository = transactionOccurrenceTypeRepository;
    }

    @Autowired
    public void setModelMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    /**
     * Delete transaction by id
     *
     * @param transactionId the id of the transaction
     * @return deleted or not of type boolean
     */
    public boolean deleteTransaction(int transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
            return true;
        } else {
            throw new ResourceNotFoundException("Transaction not found");
        }
    }

    /**
     * Update the transaction
     *
     * @param transactionRequest transaction object containing the new values
     * @return the updated transaction
     */
    public TransactionDTO updateTransaction(TransactionRequestDTO transactionRequest) {
        if (transactionRepository.existsById(transactionRequest.getId())) {
            return createUpdateTransactionDTO(transactionRequest);
        } else {
            throw new ResourceNotFoundException("Transaction not found");
        }
    }

    /**
     * Add a new transaction
     *
     * @param transactionRequest transaction object to be added
     * @return transaction object that has been added
     */
    public TransactionDTO insertTransaction(TransactionRequestDTO transactionRequest) {
        return createUpdateTransactionDTO(transactionRequest);
    }

    /**
     * Get a specific transaction by id
     *
     * @param transactionId the id of a transaction
     * @return a transaction object
     */
    public TransactionDTO getTransaction(int transactionId) {
        return transactionMapper.mapToTransactionDTO(transactionRepository
                .findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found")));
    }


    /**
     * Get all transactions
     *
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactions(Predicate predicate, Pageable pageable) {
        predicate = returnPredicateWhenNull(predicate);

        return transactionRepository
                .findAll(predicate, pageable)
                .stream()
                .map(transactionMapper::mapToTransactionDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all transaction types
     *
     * @return a list of transaction types
     */
    public List<TransactionTypeDTO> getAllTransactionTypes() {
        return transactionTypeRepository
                .findAll()
                .stream()
                .map(transactionMapper::mapToTransactionTypeDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all transaction occurrence types
     *
     * @return a list of transaction occurrence types
     */
    public List<OccurrenceDTO> getAllTransactionOccurrenceTypes() {
        return transactionOccurrenceTypeRepository
                .findAll()
                .stream()
                .map(transactionMapper::mapToTransactionOccurrenceDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all transactions of a specific user
     *
     * @param userId    the id of a user
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactionsByUserId(int userId, Predicate predicate, Pageable pageable) {
        predicate = QTransaction.transaction.user.id.eq(userId).and(predicate);
        List<Transaction> transactions = transactionRepository.findAll(predicate, pageable).getContent();

        return transactionMapper.mapToTransactionDTOList(transactions);
    }

    /**
     * Create or update a transaction
     *
     * @param transactionRequest the object containing new values
     * @return transaction containing the updated values
     */
    private TransactionDTO createUpdateTransactionDTO(TransactionRequestDTO transactionRequest) {
        Transaction transaction = transactionMapper.mapToTransactionEntity(transactionRequest);
        Transaction savedTransactionEntity = transactionRepository.save(transaction);

        return transactionMapper.mapToTransactionDTO(savedTransactionEntity);
    }

    private Predicate returnPredicateWhenNull(Predicate predicate) {
        if (predicate == null) {
            return QTransaction.transaction.id.ne(-1); // bug workaround of QueryDSL Web Support, it returns null when no matching criteria is passed in
        }
        return predicate;
    }
}
