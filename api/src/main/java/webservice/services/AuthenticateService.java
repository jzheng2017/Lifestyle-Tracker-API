package webservice.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.CredentialDTO;
import webservice.dto.TokenDTO;
import webservice.entities.User;
import webservice.exceptions.BadCredentialsException;
import webservice.exceptions.ResourceNotFoundException;
import webservice.exceptions.UnauthorizedActionException;
import webservice.repositories.UserRepository;
import webservice.services.interfaces.HashService;

import java.security.Key;

@Service
public class AuthenticateService {

    private UserRepository userRepository;
    private KeyService keyService;
    private HashService hashService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setKeyService(KeyService keyService) {
        this.keyService = keyService;
    }

    @Autowired
    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    public TokenDTO authenticateUser(CredentialDTO credentials) {
        User user = userRepository.findByUsername(credentials.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!hashService.valid(credentials.getPassword(), user.getPassword())) { //checks for validity of password
            throw new BadCredentialsException("Invalid login information");
        }
        return new TokenDTO(Jwts.builder().setSubject(user.getId().toString()).signWith(keyService.getSecretKey()).compact());
    }

    public Boolean authenticateToken(String token) {
        try {
            Key key = keyService.getSecretKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            throw new UnauthorizedActionException("Token invalid");
        }
    }


}
