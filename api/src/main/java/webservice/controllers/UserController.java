package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.RegistrationDTO;
import webservice.dto.TransactionDTO;
import webservice.dto.UserDTO;
import webservice.services.TransactionService;
import webservice.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private TransactionService transactionService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // USER CRUDS
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody RegistrationDTO user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    //USER TRANSACTION CRUDS

    @GetMapping("{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactions(@PathVariable("id") int userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId));
    }

    @GetMapping("{id}/transactions/type/{type}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByType(@PathVariable("id") int userId, @PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByType(userId, transactionType));
    }

    @GetMapping("{id}/transactions/occurrence/{occurrence}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByOccurrence(@PathVariable("id") int userId, @PathVariable("occurrence") String occurrenceType) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByOccurence(userId, occurrenceType));
    }

    @GetMapping("{userId}/transactions/category/{categoryId}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByCategory(@PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByCategory(userId, categoryId));
    }
}
