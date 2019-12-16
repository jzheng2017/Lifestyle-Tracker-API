package webservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.services.KeyService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class JwtUtil {
    private KeyService keyService;

    @Autowired
    public void setKeyService(KeyService keyService) {
        this.keyService = keyService;
    }

    public boolean isTokenValid(String token){
        return !isTokenExpired(token);
    }

    public String generateToken(String subject, int expirationTimeFromNowInSeconds) {
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + expirationTimeFromNowInSeconds * 1000);
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration)
                .signWith(keyService.getSecretKey())
                .compact();
    }

    public Claims getBodyFromToken(String token){
        return Jwts.parser().setSigningKey(keyService.getSecretKey()).parseClaimsJws(token).getBody();
    }
    public boolean isTokenExpired(String token){
        final Date expiration = getBodyFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

}
