package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.TransactionDTO;
import webservice.dto.TransactionRequest;
import webservice.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/income/all")
    public ResponseEntity getAllIncome(){
        return ResponseEntity.ok(transactionService.getAllIncome());
    }

    @GetMapping("/expense/all")
    public ResponseEntity getAllExpense(){
        return ResponseEntity.ok(transactionService.getAllExpense());
    }

    @GetMapping("{id}")
    public ResponseEntity getTransaction(@PathVariable("id") int transactionId){
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    @PostMapping
    public ResponseEntity addTransaction(TransactionRequest transaction) {
        return ResponseEntity.ok(transactionService.insertTransaction(transaction));
    }

    @PutMapping
    public ResponseEntity updateTransaction(TransactionDTO transaction){
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") int transactionId){
        return ResponseEntity.ok(transactionService.deleteTransaction(transactionId));
    }
}
