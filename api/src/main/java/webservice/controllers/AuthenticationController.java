package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservice.dto.CredentialDTO;
import webservice.dto.TokenDTO;
import webservice.services.AuthenticateService;
import webservice.services.UserService;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private AuthenticateService authenticateService;

    @Autowired
    public void setUserService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@Valid @RequestBody CredentialDTO credentials) {
        return ResponseEntity.ok(authenticateService.authenticateUser(credentials));
    }
    @PostMapping("/token")
    public ResponseEntity authenticate(@RequestBody TokenDTO token) {
        return ResponseEntity.ok(authenticateService.authenticateToken(token.getToken()));
    }

}
