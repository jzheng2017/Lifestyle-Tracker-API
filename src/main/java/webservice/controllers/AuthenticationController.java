package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservice.dto.CredentialDTO;
import webservice.dto.TokenDTO;
import webservice.services.AuthenticateService;

import javax.validation.Valid;

/**
 * This controller has been deprecated as of v1.0.0. The authentication process now uses a filter.
 *
 * @see webservice.security.filters.AuthenticationFilter
 */
@Deprecated
@RestController
public class AuthenticationController {

    private AuthenticateService authenticateService;

    @Autowired
    public void setUserService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authenticate(@Valid @RequestBody CredentialDTO credentials) {
        return ResponseEntity.ok(authenticateService.authenticateUser(credentials));
    }

    @PostMapping("/token")
    public ResponseEntity<Boolean> authenticate(@RequestBody TokenDTO token) {
        return ResponseEntity.ok(authenticateService.authenticateToken(token.getToken()));
    }

}
