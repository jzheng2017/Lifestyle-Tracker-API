package webservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.entities.OccurrenceType;
import webservice.entities.Transaction;
import webservice.entities.TransactionType;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.TransactionOccurrenceTypeRepository;
import webservice.repositories.TransactionRepository;
import webservice.repositories.TransactionTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionOccurrenceTypeRepository transactionOccurrenceTypeRepository;
    private ModelMapper modelMapper;

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
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Get all transactions by user id
     *
     * @param userId the id of a user
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactionsByUserId(int userId) {
        return transactionRepository.findAllByUserId(userId).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions by user id of specific transaction type
     *
     * @param userId the id of a user
     * @param type   type of a transaction (ex. income)
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllUserTransactionsByType(int userId, String type) {
        return transactionRepository.findAllByUserIdAndTransactionType_Type(userId, type).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
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
        return modelMapper.map(transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException("Transaction not found")), TransactionDTO.class);
    }

    /**
     * Get all transactions by type
     *
     * @param transactionType type of a transaction (ex. income)
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactionsByType(String transactionType) {
        return transactionRepository.findAllByTransactionType_Type(transactionType).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions
     *
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactions() {
        return ((List<Transaction>) transactionRepository.findAll()).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions by user id of a specific category
     *
     * @param userId     the id of a user
     * @param categoryId the id of a category
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllUserTransactionsByCategory(int userId, int categoryId) {
        return transactionRepository.findAllByUserIdAndCategoryId(userId, categoryId).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transaction types
     *
     * @return a list of transaction types
     */
    public List<TransactionTypeDTO> getAllTransactionTypes() {
        return ((List<TransactionType>) transactionTypeRepository.findAll()).stream().map(entity -> modelMapper.map(entity, TransactionTypeDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transaction occurrence types
     *
     * @return a list of transaction occurrence types
     */
    public List<OccurrenceDTO> getAllTransactionOccurrenceTypes() {
        return ((List<OccurrenceType>) transactionOccurrenceTypeRepository.findAll()).stream().map(entity -> modelMapper.map(entity, OccurrenceDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions by occurrence type
     *
     * @param occurrenceType the name of an occurrence type
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactionsByOccurrence(String occurrenceType) {
        return transactionRepository.findAllByOccurrence_Name(occurrenceType).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions by category id
     *
     * @param categoryId the id of a category
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllTransactionsByCategory(int categoryId) {
        return transactionRepository.findAllByCategory_Id(categoryId).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Get all transactions by user id of a specific occurrence type
     *
     * @param userId         the id of a user
     * @param occurrenceType the transaction occurrence type (ex. monthly)
     * @return a list of transactions
     */
    public List<TransactionDTO> getAllUserTransactionsByOccurrence(int userId, String occurrenceType) {
        return transactionRepository.findAllByUserIdAndOccurrence_Name(userId, occurrenceType).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    /**
     * Create or update a transaction
     *
     * @param transactionRequest the object containing new values
     * @return transaction containing the updated values
     */
    private TransactionDTO createUpdateTransactionDTO(TransactionRequestDTO transactionRequest) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);

        return modelMapper.map(transactionRepository.save(transaction), TransactionDTO.class);
    }
}
