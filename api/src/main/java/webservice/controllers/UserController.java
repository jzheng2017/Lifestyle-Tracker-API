package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.dto.Registration;
import webservice.dto.UserDTO;
import webservice.services.TransactionService;
import webservice.services.UserService;

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
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("update")
    public ResponseEntity updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody Registration user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    //USER TRANSACTION CRUDS

    @GetMapping("{id}/transactions")
    public ResponseEntity getAllUserTransactions(@PathVariable("id") int userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId));
    }

    @GetMapping("{id}/transactions/{type}")
    public ResponseEntity getAllUserExpenses(@PathVariable("id") int userId, @PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByType(userId, transactionType));
    }

}
