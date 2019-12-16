package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.TransactionDTO;
import webservice.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{type}/all")
    public ResponseEntity getAllTransactionsByType(@PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllByType(transactionType));
    }

    @GetMapping("{id}")
    public ResponseEntity getTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    @PostMapping("/create")
    public ResponseEntity addTransaction(@RequestBody TransactionDTO transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    @PutMapping
    public ResponseEntity updateTransaction(@RequestBody TransactionDTO transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") int transactionId) {
        return ResponseEntity.ok(transactionService.deleteTransaction(transactionId));
    }

    @GetMapping("/types")
    public ResponseEntity getAllTransactionTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionTypes());
    }

    @GetMapping("/occurrence-types")
    public ResponseEntity getAllTransactionOccurrenceTypes() {
        return ResponseEntity.ok(transactionService.getAllTransactionOccurrenceTypes());
    }
}
