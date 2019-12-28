package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequestDTO;
import webservice.dto.TransactionTypeDTO;
import webservice.entities.TransactionType;
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

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                   @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok(transactionService.getAllTransactions(order, orderBy));
    }

    @GetMapping("/type/{type}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByType(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                         @PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByType(order, orderBy, transactionType));
    }

    @GetMapping("/occurrence/{occurrence}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByOccurrence(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                               @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                               @PathVariable("occurrence") String occurrenceType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByOccurrence(order, orderBy, occurrenceType));
    }

    @GetMapping("/category/{categoryId}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByCategory(@RequestParam(name = "order", defaultValue = "asc") String order,
                                                                             @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                                                                             @PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByCategory(order, orderBy, categoryId));
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    @PutMapping("/update")
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.deleteTransaction(transactionId));
    }

    @GetMapping("/types")
    public ResponseEntity<List<TransactionTypeDTO>> getAllTransactionTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionTypes());
    }

    @GetMapping("/occurrence-types")
    public ResponseEntity<List<OccurrenceDTO>> getAllTransactionOccurrenceTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionOccurrenceTypes());
    }
}
