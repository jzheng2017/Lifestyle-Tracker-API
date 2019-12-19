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

    /**
     * Validates the JWT token
     *
     * @param token a JWT token
     * @return whether the passed in JWT token is valid
     */
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    /**
     * Generates a JWT token
     *
     * @param subject    a JWT subject claim
     * @param expiration expiration date of the token (in seconds)
     * @return generated JWT token
     */
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

    /**
     * Generates a JWT token
     *
     * @param subject a JWT subject claim
     * @return generated JWT token
     */
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

    /**
     * Get body (claims) of a JWT token
     *
     * @param token a JWT token
     * @return all claims of the passed in JWT token
     */
    public Claims getBodyFromToken(String token) {
        return Jwts.parser().setSigningKey(keyService.getSecretKey()).parseClaimsJws(token).getBody();
    }

    /**
     * Checks whether the passed in token has expired
     *
     * @param token a JWT token
     * @return whether the token has expired
     */
    public boolean isTokenExpired(String token) {
        final Date expiration = getBodyFromToken(token).getExpiration();
        return expiration.before(new Date());
    }


}
