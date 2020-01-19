package webservice.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.entities.Transaction;
import webservice.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get all transactions
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@QuerydslPredicate(root = Transaction.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAllTransactions(predicate, pageable));
    }

    /**
     * Get a single transaction
     *
     * @param transactionId the id of a transaction
     * @return ResponseEntity object containing the requested transaction
     */
    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    /**
     * Create a new transaction
     *
     * @param transaction the request object containing the transaction
     * @return ResponseEntity object containing the added transaction
     */
    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    /**
     * Update a single transaction
     *
     * @param transaction the request object containing a transaction with new values
     * @return ResponseEntity object containing the updated transaction
     */
    @PutMapping
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    /**
     * Delete a single transaction
     *
     * @param transactionId the id of a transaction
     * @return ResponseEntity object containing a boolean value whether the transaction has been successfully deleted or not
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.deleteTransaction(transactionId));
    }

    /**
     * Get all transaction types
     *
     * @return ResponseEntity object containing a list of all transaction types
     */
    @GetMapping("/types")
    public ResponseEntity<List<TransactionTypeDTO>> getAllTransactionTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionTypes());
    }

    /**
     * Get all transaction occurrence types
     *
     * @return ResponseEntity object containing a list of all transaction occurrence types
     */
    @GetMapping("/occurrence-types")
    public ResponseEntity<List<OccurrenceDTO>> getAllTransactionOccurrenceTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionOccurrenceTypes());
    }
}
