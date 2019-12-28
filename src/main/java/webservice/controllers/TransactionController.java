package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
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
     * @param order   the order in which the list should be ordered (ex. ascending)
     * @param orderBy the field that should be ordered on
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                   @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok(transactionService.getAllTransactions(order, orderBy));
    }

    /**
     * Get all transactions of a specific type
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param order           the order in which the list should be ordered (ex. ascending)
     * @param orderBy         the field that should be ordered on
     * @param transactionType the type of a transaction (ex. income)
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("/type/{type}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByType(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                         @PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByType(order, orderBy, transactionType));
    }

    /**
     * Get all transactions of a specific occurrence
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param order          the order in which the list should be ordered (ex. ascending)
     * @param orderBy        the field that should be ordered on
     * @param occurrenceType the occurrence of a transaction (ex. monthly)
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("/occurrence/{occurrence}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByOccurrence(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                               @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                               @PathVariable("occurrence") String occurrenceType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByOccurrence(order, orderBy, occurrenceType));
    }

    /**
     * Get all transactions of a specific category
     * <br>
     * If the order or order by field is not specified, it will use the default value
     *
     * @param order      the order in which the list should be ordered (ex. ascending)
     * @param orderBy    the field that should be ordered on
     * @param categoryId the id of a category
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("/category/{categoryId}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByCategory(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                             @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                             @PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByCategory(order, orderBy, categoryId));
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
    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    /**
     * Update a single transaction
     *
     * @param transaction the request object containing a transaction with new values
     * @return ResponseEntity object containing the updated transaction
     */
    @PutMapping("/update")
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
