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
import webservice.exceptions.ResourceNotFoundException;
import webservice.exceptions.UnauthorizedActionException;
import webservice.repositories.UserRepository;

import java.security.Key;

@Service
public class AuthenticateService {

    private UserRepository userRepository;
    private KeyService keyService;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setKeyService(KeyService keyService) {
        this.keyService = keyService;
    }

    public TokenDTO authenticateUser(CredentialDTO credentials) {
        User user = userRepository.findByUsername(credentials.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
