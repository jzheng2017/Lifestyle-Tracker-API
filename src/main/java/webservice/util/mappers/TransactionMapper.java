package webservice.util.mappers;

import org.springframework.stereotype.Service;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.entities.OccurrenceType;
import webservice.entities.Transaction;
import webservice.entities.TransactionType;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionMapper extends ClassMapper {

    /**
     * Maps a TransactionRequestDTO to a Transaction entity
     *
     * @param transactionRequest incoming request
     * @return a transaction entity
     */
    public Transaction mapToTransactionEntity(TransactionRequestDTO transactionRequest) {
        return modelMapper.map(transactionRequest, Transaction.class);
    }

    /**
     * Maps a Transaction entity to a Transaction DTO
     *
     * @param transactionEntity a transaction entity
     * @return a transaction dto
     */
    public TransactionDTO mapToTransactionDTO(Transaction transactionEntity) {
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }

    /**
     * Maps a list of transaction entities to a list of transaction dto's
     *
     * @param transactions a list of transaction entities
     * @return a list of transaction dto
     */
    public List<TransactionDTO> mapToTransactionDTOList(List<Transaction> transactions) {
        return transactions
                .stream()
                .map(entity -> modelMapper.map(entity, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    public TransactionTypeDTO mapToTransactionTypeDTO(TransactionType transactionType) {
        return modelMapper.map(transactionType, TransactionTypeDTO.class);
    }

    public OccurrenceDTO mapToTransactionOccurrenceDTO(OccurrenceType occurrenceType) {
        return modelMapper.map(occurrenceType, OccurrenceDTO.class);
    }
}