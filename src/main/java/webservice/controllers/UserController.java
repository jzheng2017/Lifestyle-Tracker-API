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

    /**
     * Get all users
     *
     * @return ResponseEntity object containing a list of users
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Get a single user
     *
     * @param userId the id of a user
     * @return ResponseEntity object containing a user
     */
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    /**
     * Delete a single user
     *
     * @param userId the id of a user
     * @return a boolean value, true if user has been deleted, otherwise false
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    /**
     * Update a user
     *
     * @param user object containing the new values
     * @return user object containing the updated values
     */
    @PutMapping("update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    /**
     * Add a single user
     *
     * @param user object containing the registration values
     * @return registered user
     */
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody RegistrationDTO user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    /**
     * Get all transactions of a specific user
     *
     * @param userId the id of a user
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactions(@PathVariable("id") int userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId));
    }

    /**
     * Get all transactions of a specific type of a specific user
     *
     * @param userId          the id of a user
     * @param transactionType the type of a transaction (ex. income)
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("{id}/transactions/type/{type}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByType(@PathVariable("id") int userId, @PathVariable("type") String transactionType) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByType(userId, transactionType));
    }

    /**
     * Get all transactions of a specific occurrence of a specific user
     *
     * @param userId         the id of a user
     * @param occurrenceType the occurrence type of a transactions (ex. monthly)
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("{id}/transactions/occurrence/{occurrence}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByOccurrence(@PathVariable("id") int userId, @PathVariable("occurrence") String occurrenceType) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByOccurrence(userId, occurrenceType));
    }

    /**
     * Get all transactions of a specific category of a specific user
     *
     * @param userId     the id of a user
     * @param categoryId the id of a category
     * @return ResponseEntity object containing a list of transactions
     */
    @GetMapping("{userId}/transactions/category/{categoryId}/all")
    public ResponseEntity<List<TransactionDTO>> getAllUserTransactionsByCategory(@PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(transactionService.getAllUserTransactionsByCategory(userId, categoryId));
    }
}
