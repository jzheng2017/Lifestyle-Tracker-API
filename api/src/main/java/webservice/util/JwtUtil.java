package webservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.services.AppConfigService;
import webservice.services.KeyService;

import java.util.Date;

@Service
public class JwtUtil {
    private KeyService keyService;
    private AppConfigService appConfigService;

    @Autowired
    public void setKeyService(KeyService keyService) {
        this.keyService = keyService;
    }

    @Autowired
    public void setAppConfigService(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public String generateToken(String subject, int expiration) {
        final Date now = new Date();
        final long expirationInSeconds = now.getTime() + expiration * 1000;
        final Date expirationDate = new Date(expirationInSeconds);
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(keyService.getSecretKey())
                .compact();
    }

    public String generateToken(String subject) {
        final int expiration = appConfigService.getTokenExpiration();
        final Date now = new Date();
        final long expirationInSeconds = now.getTime() + expiration * 1000;
        final Date expirationDate = new Date(expirationInSeconds);

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(keyService.getSecretKey())
                .compact();
    }

    public String generateToken(String subject) {
        final int expiration = appConfigService.getTokenExpiration();
        final Date now = new Date();
        final long expirationInSeconds = now.getTime() + expiration * 1000;
        final Date expirationDate = new Date(expirationInSeconds);
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(keyService.getSecretKey())
                .compact();
    }

    public Claims getBodyFromToken(String token) {
        return Jwts.parser().setSigningKey(keyService.getSecretKey()).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getBodyFromToken(token).getExpiration();
        return expiration.before(new Date());
    }


}
