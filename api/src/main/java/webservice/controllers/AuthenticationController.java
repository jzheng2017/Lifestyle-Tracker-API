package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservice.dto.CredentialDTO;
import webservice.services.UserService;

@RestController
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody CredentialDTO credentials) {
        return ResponseEntity.ok(userService.authenticateUser(credentials));
    }

}
