package webservice.security.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import webservice.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER_STRING = "Authorization";
    private JwtUtil jwtUtil;


    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION_HEADER_STRING);
        final boolean isValidHeader = header == null || !header.startsWith(TOKEN_PREFIX);

        if (isValidHeader) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_STRING);

        if (header != null) {
            String token = getTokenFromHeaderString(header);
            String user = getSubjectFromJwtTokenIfTokenIsValid(token);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

    private String getTokenFromHeaderString(String header) {
        return header.replace(TOKEN_PREFIX, "");
    }


    private String getSubjectFromJwtTokenIfTokenIsValid(String token) {
        if (jwtUtil.isTokenValid(token)) {
            return jwtUtil.getBodyFromToken(token).getSubject();
        } else {
            return null;
        }
    }
}
