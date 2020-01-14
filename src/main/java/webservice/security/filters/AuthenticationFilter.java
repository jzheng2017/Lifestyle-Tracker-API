package webservice.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import webservice.dto.CredentialDTO;
import webservice.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String TOKEN_PREFIX = "Bearer ";
    private final String AUTHORIZATION_HEADER_STRING = "Authorization";
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;


    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            CredentialDTO credentials = getCredentialsFromRequest(req);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword(),
                    new ArrayList<>());

            return authenticateUser(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CredentialDTO getCredentialsFromRequest(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = getInputFromRequest(request);

        return mapRequestValueToCredentialsDTO(inputStream);
    }

    private CredentialDTO mapRequestValueToCredentialsDTO(ServletInputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(inputStream, CredentialDTO.class);
    }

    private ServletInputStream getInputFromRequest(HttpServletRequest request) throws IOException {
        return request.getInputStream();
    }

    private Authentication authenticateUser(UsernamePasswordAuthenticationToken token) {
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) {
        String username = getUsernameFromAuthentication(authentication);
        String token = generateJwtTokenFromUsername(username);
        String headerValue = TOKEN_PREFIX + token;

        addHeaderToResponse(response, AUTHORIZATION_HEADER_STRING, headerValue);
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }

    private String generateJwtTokenFromUsername(String username) {
        return jwtUtil.generateToken(username);
    }

    private void addHeaderToResponse(HttpServletResponse response, String header, String headerValue) {
        response.addHeader(header, headerValue);
    }
}
