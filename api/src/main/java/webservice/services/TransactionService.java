package webservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.TransactionDTO;
import webservice.entities.Transaction;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<TransactionDTO> getAllTransactionsByUserId(int userId) {
        return transactionRepository.findAllByUserId(userId).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    public List<TransactionDTO> getAllUserTransactionsByType(int userId, String type) {
        return transactionRepository.findAllByUserIdAndTransactionType_Type(userId, type).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }


    public boolean deleteTransaction(int transactionId) {
        return false;
    }

    public TransactionDTO updateTransaction(TransactionDTO transaction) {
        return null;
    }

    public TransactionDTO insertTransaction(TransactionDTO transaction) {
        return modelMapper.map(transactionRepository.save(modelMapper.map(transaction, Transaction.class)), TransactionDTO.class);
    }

    public TransactionDTO getTransaction(int transactionId) {
        return modelMapper.map(transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException("Transaction not found")), TransactionDTO.class);
    }

    public List<TransactionDTO> getAllByType(String transactionType) {
        return transactionRepository.findAllByTransactionType_Type(transactionType).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    public List<TransactionDTO> getAllTransactions() {
        return ((List<Transaction>) transactionRepository.findAll()).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }

    public List<TransactionDTO> getAllUserTransactionsByCategory(int userId, int categoryId) {
        return transactionRepository.findAllByUserIdAndCategoryId(userId, categoryId).stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).collect(Collectors.toList());
    }
}
