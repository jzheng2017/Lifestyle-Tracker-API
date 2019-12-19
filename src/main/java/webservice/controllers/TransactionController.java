package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.OccurrenceDTO;
import webservice.dto.TransactionDTO;
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
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/type/{type}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByType(@PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByType(transactionType));
    }

    @GetMapping("/occurrence/{occurrence}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByOccurrence(@PathVariable("occurrence") String occurrenceType) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByOccurrence(occurrenceType));
    }

    @GetMapping("/category/{categoryId}/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByCategory(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByCategory(categoryId));
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    @PutMapping
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO transaction) {
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